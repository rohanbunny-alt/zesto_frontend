import express from 'express';
import http from 'http';
import { Server } from 'socket.io';
import cors from 'cors';
import { categories, coupons, initialRestaurants, sampleRiders, initialOrders } from './data.js';

const app = express();
const server = http.createServer(app);

const io = new Server(server, {
  cors: {
    origin: '*',
    methods: ['GET', 'POST', 'PUT', 'DELETE']
  }
});

app.use(cors());
app.use(express.json());

// In-memory data storage
let restaurants = [...initialRestaurants];
let orders = [...initialOrders];
let riders = [...sampleRiders];

// Helper to calculate analytics stats
function getAdminStats() {
  const totalOrders = orders.length;
  const totalRevenue = orders.reduce((sum, o) => sum + (o.status !== 'Cancelled' ? o.grandTotal : 0), 0);
  const activeOrdersCount = orders.filter(o => o.status !== 'Delivered' && o.status !== 'Cancelled').length;
  const activeRestaurants = restaurants.filter(r => r.isOpen).length;
  const activeRiders = riders.length;

  const ordersByStatus = {
    Placed: orders.filter(o => o.status === 'Placed').length,
    Confirmed: orders.filter(o => o.status === 'Confirmed').length,
    Preparing: orders.filter(o => o.status === 'Preparing').length,
    'On The Way': orders.filter(o => o.status === 'On The Way').length,
    Delivered: orders.filter(o => o.status === 'Delivered').length,
    Cancelled: orders.filter(o => o.status === 'Cancelled').length,
  };

  return {
    totalRevenue,
    totalOrders,
    activeOrdersCount,
    activeRestaurants,
    activeRiders,
    ordersByStatus
  };
}

// REST APIs
app.get('/api/categories', (req, res) => {
  res.json(categories);
});

app.get('/api/coupons', (req, res) => {
  res.json(coupons);
});

app.post('/api/coupons/apply', (req, res) => {
  const { code, cartAmount } = req.body;
  const coupon = coupons.find(c => c.code.toUpperCase() === code?.toUpperCase());

  if (!coupon) {
    return res.status(404).json({ success: false, message: 'Invalid coupon code' });
  }

  if (cartAmount < coupon.minOrder) {
    return res.status(400).json({
      success: false,
      message: `Minimum order amount for ${coupon.code} is ₹${coupon.minOrder}`
    });
  }

  let discount = 0;
  if (coupon.isFreeDelivery) {
    discount = 35; // Standard delivery fee waiver
  } else {
    discount = Math.min(Math.round((cartAmount * coupon.discountPercentage) / 100), coupon.maxDiscount);
  }

  res.json({
    success: true,
    coupon,
    discountAmount: discount,
    message: `Coupon '${coupon.code}' applied successfully!`
  });
});

app.get('/api/restaurants', (req, res) => {
  const { search, category, vegOnly } = req.query;
  let result = [...restaurants];

  if (vegOnly === 'true') {
    result = result.filter(r => r.isPureVeg);
  }

  if (category) {
    result = result.filter(r =>
      r.cuisine.some(c => c.toLowerCase().includes(category.toLowerCase())) ||
      r.menu.some(m => m.category.toLowerCase().includes(category.toLowerCase()))
    );
  }

  if (search) {
    const q = search.toLowerCase();
    result = result.filter(r =>
      r.name.toLowerCase().includes(q) ||
      r.cuisine.some(c => c.toLowerCase().includes(q)) ||
      r.menu.some(m => m.name.toLowerCase().includes(q))
    );
  }

  res.json(result);
});

app.get('/api/restaurants/:id', (req, res) => {
  const rest = restaurants.find(r => r.id === req.params.id);
  if (!rest) return res.status(404).json({ message: 'Restaurant not found' });
  res.json(rest);
});

app.post('/api/restaurants', (req, res) => {
  const { name, image, cuisine, costForTwo, location, isPureVeg, offer } = req.body;
  const newRest = {
    id: `rest-${Date.now()}`,
    name,
    image: image || 'https://images.unsplash.com/photo-1517248135467-4c7edcad34c4?w=600&auto=format&fit=crop&q=80',
    cuisine: Array.isArray(cuisine) ? cuisine : cuisine.split(',').map(c => c.trim()),
    rating: 4.5,
    ratingCount: 'NEW',
    deliveryTime: '25-30 min',
    costForTwo: Number(costForTwo) || 350,
    location: location || 'Bangalore Central',
    isPureVeg: !!isPureVeg,
    offer: offer || '20% OFF',
    isOpen: true,
    menu: []
  };

  restaurants.unshift(newRest);
  io.emit('restaurants:updated', restaurants);
  io.emit('admin:stats_updated', getAdminStats());
  res.status(201).json(newRest);
});

app.post('/api/restaurants/:id/menu', (req, res) => {
  const rest = restaurants.find(r => r.id === req.params.id);
  if (!rest) return res.status(404).json({ message: 'Restaurant not found' });

  const { name, price, category, isVeg, description, image } = req.body;
  const newItem = {
    id: `item-${Date.now()}`,
    name,
    price: Number(price),
    category: category || 'Main Course',
    isVeg: !!isVeg,
    isBestseller: false,
    rating: 4.5,
    ratingCount: 1,
    description: description || '',
    image: image || 'https://images.unsplash.com/photo-1546069901-ba9599a7e63c?w=400&auto=format&fit=crop&q=80'
  };

  rest.menu.push(newItem);
  io.emit('restaurants:updated', restaurants);
  res.status(201).json(newItem);
});

app.delete('/api/restaurants/:id/menu/:itemId', (req, res) => {
  const rest = restaurants.find(r => r.id === req.params.id);
  if (!rest) return res.status(404).json({ message: 'Restaurant not found' });

  rest.menu = rest.menu.filter(m => m.id !== req.params.itemId);
  io.emit('restaurants:updated', restaurants);
  res.json({ success: true, message: 'Item deleted' });
});

// Orders Endpoints
app.get('/api/orders', (req, res) => {
  res.json(orders);
});

app.get('/api/orders/:orderId', (req, res) => {
  const order = orders.find(o => o.orderId === req.params.orderId);
  if (!order) return res.status(404).json({ message: 'Order not found' });
  res.json(order);
});

app.post('/api/orders', (req, res) => {
  const {
    customerName,
    customerPhone,
    deliveryAddress,
    restaurantId,
    items,
    itemTotal,
    taxesAndFee,
    discount,
    grandTotal,
    paymentMethod
  } = req.body;

  const rest = restaurants.find(r => r.id === restaurantId);
  const randomRider = riders[Math.floor(Math.random() * riders.length)];

  const newOrder = {
    orderId: `ORD-${Math.floor(1000 + Math.random() * 9000)}`,
    customerName: customerName || 'Valued Customer',
    customerPhone: customerPhone || '+91 98765 43210',
    deliveryAddress: deliveryAddress || '123 Swiggy Street, Koramangala, Bangalore',
    restaurantId: restaurantId || (rest ? rest.id : 'rest-1'),
    restaurantName: rest ? rest.name : 'Swiggy Partner Restaurant',
    items: items || [],
    itemTotal: itemTotal || 0,
    taxesAndFee: taxesAndFee || 35,
    discount: discount || 0,
    grandTotal: grandTotal || 0,
    paymentMethod: paymentMethod || 'UPI',
    paymentStatus: 'Paid',
    status: 'Placed',
    rider: randomRider,
    placedAt: new Date().toISOString(),
    etaMinutes: 30
  };

  orders.unshift(newOrder);

  // Broadcast to Admin and tracking listeners
  io.emit('order:created', newOrder);
  io.emit('admin:stats_updated', getAdminStats());

  res.status(201).json({ success: true, order: newOrder });
});

app.put('/api/orders/:orderId/status', (req, res) => {
  const { status } = req.body;
  const order = orders.find(o => o.orderId === req.params.orderId);
  if (!order) return res.status(404).json({ message: 'Order not found' });

  order.status = status;

  if (status === 'Delivered') {
    order.etaMinutes = 0;
  } else if (status === 'On The Way') {
    order.etaMinutes = 10;
  } else if (status === 'Preparing') {
    order.etaMinutes = 20;
  }

  // Socket notification
  io.emit('order:updated', order);
  io.emit('admin:stats_updated', getAdminStats());

  res.json({ success: true, order });
});

app.get('/api/stats', (req, res) => {
  res.json(getAdminStats());
});

app.get('/api/riders', (req, res) => {
  res.json(riders);
});

// Socket.io Connection Logic
io.on('connection', (socket) => {
  console.log(`⚡ Client connected: ${socket.id}`);

  socket.emit('admin:stats_updated', getAdminStats());
  socket.emit('orders:list', orders);

  socket.on('join_order_room', (orderId) => {
    socket.join(`order_${orderId}`);
    const order = orders.find(o => o.orderId === orderId);
    if (order) {
      socket.emit('order:updated', order);
    }
  });

  socket.on('update_order_status', ({ orderId, status }) => {
    const order = orders.find(o => o.orderId === orderId);
    if (order) {
      order.status = status;
      io.emit('order:updated', order);
      io.to(`order_${orderId}`).emit('order:updated', order);
      io.emit('admin:stats_updated', getAdminStats());
    }
  });

  socket.on('disconnect', () => {
    console.log(`🔥 Client disconnected: ${socket.id}`);
  });
});

const PORT = process.env.PORT || 5000;
server.listen(PORT, () => {
  console.log(`🚀 Zesto Express & Socket.io server running on http://localhost:${PORT}`);
});
