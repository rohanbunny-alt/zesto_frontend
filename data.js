export const categories = [
  { id: '1', name: 'Biryani', image: 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=300&auto=format&fit=crop&q=80' },
  { id: '2', name: 'Pizzas', image: 'https://images.unsplash.com/photo-1513104890138-7c749659a591?w=300&auto=format&fit=crop&q=80' },
  { id: '3', name: 'Burgers', image: 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=300&auto=format&fit=crop&q=80' },
  { id: '4', name: 'Chinese', image: 'https://images.unsplash.com/photo-1585032226651-759b368d7246?w=300&auto=format&fit=crop&q=80' },
  { id: '5', name: 'South Indian', image: 'https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=300&auto=format&fit=crop&q=80' },
  { id: '6', name: 'Desserts', image: 'https://images.unsplash.com/photo-1551024709-8f23befc6f87?w=300&auto=format&fit=crop&q=80' },
  { id: '7', name: 'Rolls', image: 'https://images.unsplash.com/photo-1626777552726-4a6b54c97e46?w=300&auto=format&fit=crop&q=80' },
  { id: '8', name: 'North Indian', image: 'https://images.unsplash.com/photo-1588166524941-3bf61a9c41db?w=300&auto=format&fit=crop&q=80' },
];

export const coupons = [
  { code: 'ZESTO50', discountPercentage: 50, maxDiscount: 120, minOrder: 199, description: '50% OFF up to ₹120 on orders above ₹199' },
  { code: 'SWIGGYSTYLE', discountPercentage: 40, maxDiscount: 100, minOrder: 149, description: '40% OFF up to ₹100' },
  { code: 'FREEDEL', discountPercentage: 100, isFreeDelivery: true, maxDiscount: 40, minOrder: 99, description: 'Free Delivery on all orders' },
  { code: 'WELCOME200', discountPercentage: 60, maxDiscount: 200, minOrder: 399, description: 'Flat 60% OFF up to ₹200 for new foodies' }
];

export const initialRestaurants = [
  {
    id: 'rest-1',
    name: 'Royal Biryani House',
    image: 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=600&auto=format&fit=crop&q=80',
    cuisine: ['Hyderabadi Biryani', 'Mughlai', 'Kebab'],
    rating: 4.5,
    ratingCount: '5K+',
    deliveryTime: '25-30 min',
    costForTwo: 400,
    location: 'Indiranagar, Bangalore',
    isPureVeg: false,
    offer: '60% OFF up to ₹120',
    isOpen: true,
    menu: [
      {
        id: 'item-101',
        name: 'Hyderabadi Dum Chicken Biryani',
        price: 289,
        originalPrice: 350,
        category: 'Biryani',
        isVeg: false,
        isBestseller: true,
        rating: 4.7,
        ratingCount: 320,
        description: 'Authentic Basmati rice slow cooked with tender chicken pieces, aromatic herbs & spices. Served with Mirchi ka Salan & Raita.',
        image: 'https://images.unsplash.com/photo-1563379091339-03b21ab4a4f8?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-102',
        name: 'Paneer Royal Dum Biryani',
        price: 249,
        originalPrice: 299,
        category: 'Biryani',
        isVeg: true,
        isBestseller: true,
        rating: 4.4,
        ratingCount: 180,
        description: 'Soft cottage cheese marinated in tikka spices layered with fragrant Basmati rice.',
        image: 'https://images.unsplash.com/photo-1633945274405-b6c8069047b0?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-103',
        name: 'Chicken Reshmi Kebab (6 Pcs)',
        price: 319,
        category: 'Starters',
        isVeg: false,
        isBestseller: false,
        rating: 4.6,
        ratingCount: 140,
        description: 'Melt-in-mouth chicken boneless marinated in cashew paste and cream, charcoal grilled.',
        image: 'https://images.unsplash.com/photo-1599487488170-d11ec9c172f0?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-104',
        name: 'Mutton Galouti Kebab with Paratha',
        price: 369,
        category: 'Starters',
        isVeg: false,
        isBestseller: true,
        rating: 4.8,
        ratingCount: 210,
        description: 'Lucknowi delicacy minced mutton skewers served with 2 Ulta Tawa Parathas.',
        image: 'https://images.unsplash.com/photo-1544025162-d76694265947?w=400&auto=format&fit=crop&q=80'
      }
    ]
  },
  {
    id: 'rest-2',
    name: 'Pizza Oven Supreme',
    image: 'https://images.unsplash.com/photo-1513104890138-7c749659a591?w=600&auto=format&fit=crop&q=80',
    cuisine: ['Italian', 'Pizzas', 'Pasta', 'Fast Food'],
    rating: 4.3,
    ratingCount: '10K+',
    deliveryTime: '20-25 min',
    costForTwo: 500,
    location: 'Koramangala, Bangalore',
    isPureVeg: false,
    offer: 'EVERYTHING @ ₹199',
    isOpen: true,
    menu: [
      {
        id: 'item-201',
        name: 'Pepperoni & Cheese Loaded Pizza (Medium)',
        price: 399,
        originalPrice: 499,
        category: 'Pizzas',
        isVeg: false,
        isBestseller: true,
        rating: 4.8,
        ratingCount: 540,
        description: 'Classic mozzarella cheese with spicy pepperoni slices baked on sourdough base.',
        image: 'https://images.unsplash.com/photo-1628840042765-356cda07504e?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-202',
        name: 'Farmhouse Special Veg Pizza (Medium)',
        price: 329,
        originalPrice: 399,
        category: 'Pizzas',
        isVeg: true,
        isBestseller: true,
        rating: 4.5,
        ratingCount: 420,
        description: 'Delightful combo of capsicum, sweet corn, crisp capsicum, paneer and black olives.',
        image: 'https://images.unsplash.com/photo-1574071318508-1cdbab80d002?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-203',
        name: 'Garlic Breadsticks with Cheese Dip',
        price: 149,
        category: 'Sides',
        isVeg: true,
        isBestseller: true,
        rating: 4.6,
        ratingCount: 310,
        description: 'Freshly baked bread filled with garlic butter & herbs, served with spicy cheese dip.',
        image: 'https://images.unsplash.com/photo-1573140247632-f8fd74997d5c?w=400&auto=format&fit=crop&q=80'
      }
    ]
  },
  {
    id: 'rest-3',
    name: 'Burger & Craft Grill',
    image: 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=600&auto=format&fit=crop&q=80',
    cuisine: ['American', 'Burgers', 'Fries', 'Shakes'],
    rating: 4.4,
    ratingCount: '2K+',
    deliveryTime: '15-20 min',
    costForTwo: 350,
    location: 'HSR Layout, Bangalore',
    isPureVeg: false,
    offer: '50% OFF up to ₹100',
    isOpen: true,
    menu: [
      {
        id: 'item-301',
        name: 'Smokey BBQ Chicken Crunch Burger',
        price: 219,
        originalPrice: 279,
        category: 'Burgers',
        isVeg: false,
        isBestseller: true,
        rating: 4.6,
        ratingCount: 230,
        description: 'Crispy fried chicken patty glazed in house smoked BBQ sauce, sharp cheddar & lettuce.',
        image: 'https://images.unsplash.com/photo-1568901346375-23c9450c58cd?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-302',
        name: 'Ultimate Cheese Lava Veg Burger',
        price: 189,
        category: 'Burgers',
        isVeg: true,
        isBestseller: true,
        rating: 4.5,
        ratingCount: 310,
        description: 'Molten cheese patty stuffed with jalapeños & veggies topped with spicy mayo.',
        image: 'https://images.unsplash.com/photo-1550547660-d9450f859349?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-303',
        name: 'Peri Peri Salted French Fries',
        price: 119,
        category: 'Sides',
        isVeg: true,
        isBestseller: false,
        rating: 4.4,
        ratingCount: 190,
        description: 'Golden skin-on crispy fries tossed in fiery African peri peri spice mix.',
        image: 'https://images.unsplash.com/photo-1576107232684-1279f3908594?w=400&auto=format&fit=crop&q=80'
      }
    ]
  },
  {
    id: 'rest-4',
    name: 'Sagar Ratna South Delights',
    image: 'https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=600&auto=format&fit=crop&q=80',
    cuisine: ['South Indian', 'Dosa', 'Filter Coffee', 'Healthy'],
    rating: 4.7,
    ratingCount: '15K+',
    deliveryTime: '15-25 min',
    costForTwo: 250,
    location: 'Jayanagar, Bangalore',
    isPureVeg: true,
    offer: 'FLAT 20% OFF',
    isOpen: true,
    menu: [
      {
        id: 'item-401',
        name: 'Ghee Roast Masala Dosa',
        price: 139,
        category: 'South Indian',
        isVeg: true,
        isBestseller: true,
        rating: 4.9,
        ratingCount: 890,
        description: 'Crispy rice crepe roasted in pure Desi Ghee stuffed with spiced potato masala.',
        image: 'https://images.unsplash.com/photo-1610192244261-3f33de3f55e4?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-402',
        name: 'Button Idli Sambar (12 Pcs in Ghee)',
        price: 119,
        category: 'South Indian',
        isVeg: true,
        isBestseller: true,
        rating: 4.7,
        ratingCount: 450,
        description: 'Mini steamed rice cakes floating in hot drumstick sambar garnished with ghee.',
        image: 'https://images.unsplash.com/photo-1589301760014-d929f3979dbc?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-403',
        name: 'Degree Filter Coffee (200ml)',
        price: 49,
        category: 'Beverages',
        isVeg: true,
        isBestseller: true,
        rating: 4.9,
        ratingCount: 1200,
        description: 'Authentic South Indian brass filter coffee brewed fresh with thick frothed milk.',
        image: 'https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?w=400&auto=format&fit=crop&q=80'
      }
    ]
  },
  {
    id: 'rest-5',
    name: 'Wok & Dragon Chinese',
    image: 'https://images.unsplash.com/photo-1585032226651-759b368d7246?w=600&auto=format&fit=crop&q=80',
    cuisine: ['Chinese', 'Noodles', 'Dim sums', 'Momos'],
    rating: 4.2,
    ratingCount: '3K+',
    deliveryTime: '25-35 min',
    costForTwo: 450,
    location: 'MG Road, Bangalore',
    isPureVeg: false,
    offer: 'Free Dish on ₹499+',
    isOpen: true,
    menu: [
      {
        id: 'item-501',
        name: 'Hakka Noodles with Chilli Chicken',
        price: 299,
        originalPrice: 349,
        category: 'Chinese',
        isVeg: false,
        isBestseller: true,
        rating: 4.6,
        ratingCount: 310,
        description: 'Classic wok tossed veg Hakka noodles paired with spicy gravy Chilli Chicken.',
        image: 'https://images.unsplash.com/photo-1585032226651-759b368d7246?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-502',
        name: 'Steamed Chicken Momos (8 Pcs)',
        price: 169,
        category: 'Chinese',
        isVeg: false,
        isBestseller: true,
        rating: 4.5,
        ratingCount: 620,
        description: 'Juicy minced chicken dumpling served with red hot chilli chutney & clear soup.',
        image: 'https://images.unsplash.com/photo-1625220194771-7ebdea0b70b9?w=400&auto=format&fit=crop&q=80'
      }
    ]
  },
  {
    id: 'rest-6',
    name: 'Sweet Truth Bakery & Sundaes',
    image: 'https://images.unsplash.com/photo-1551024709-8f23befc6f87?w=600&auto=format&fit=crop&q=80',
    cuisine: ['Desserts', 'Cakes', 'Waffles', 'Ice Cream'],
    rating: 4.6,
    ratingCount: '4K+',
    deliveryTime: '15-20 min',
    costForTwo: 300,
    location: 'BTM Layout, Bangalore',
    isPureVeg: true,
    offer: '30% OFF',
    isOpen: true,
    menu: [
      {
        id: 'item-601',
        name: 'Choco Lava Molten Lava Cake',
        price: 109,
        originalPrice: 139,
        category: 'Desserts',
        isVeg: true,
        isBestseller: true,
        rating: 4.8,
        ratingCount: 1100,
        description: 'Warm chocolate cake filled with gooey liquid chocolate fudge center.',
        image: 'https://images.unsplash.com/photo-1606313564200-e75d5e30476c?w=400&auto=format&fit=crop&q=80'
      },
      {
        id: 'item-602',
        name: 'Nutella Belgian Waffle',
        price: 179,
        category: 'Desserts',
        isVeg: true,
        isBestseller: true,
        rating: 4.7,
        ratingCount: 430,
        description: 'Freshly baked crisp Belgian waffle drizzled generously with authentic Nutella.',
        image: 'https://images.unsplash.com/photo-1562376552-0d160a2f238d?w=400&auto=format&fit=crop&q=80'
      }
    ]
  }
];

export const sampleRiders = [
  { id: 'rider-1', name: 'Ramesh Kumar', phone: '+91 98765 43210', rating: 4.9, vehicle: 'KA-01-EQ-4821', avatar: 'https://images.unsplash.com/photo-1534528741775-53994a69daeb?w=150&auto=format&fit=crop&q=80' },
  { id: 'rider-2', name: 'Vikram Singh', phone: '+91 98123 45678', rating: 4.8, vehicle: 'KA-03-HF-9012', avatar: 'https://images.unsplash.com/photo-1507003211169-0a1dd7228f2d?w=150&auto=format&fit=crop&q=80' },
  { id: 'rider-3', name: 'Aniket Sharma', phone: '+91 99887 76655', rating: 4.7, vehicle: 'KA-05-AB-1234', avatar: 'https://images.unsplash.com/photo-1500648767791-00dcc994a43e?w=150&auto=format&fit=crop&q=80' }
];

export const initialOrders = [
  {
    orderId: 'ORD-9821',
    customerName: 'Aarav Mehta',
    customerPhone: '+91 98989 12345',
    deliveryAddress: 'Flat 402, Sunshine Apartments, Indiranagar, Bangalore',
    restaurantId: 'rest-1',
    restaurantName: 'Royal Biryani House',
    items: [
      { id: 'item-101', name: 'Hyderabadi Dum Chicken Biryani', price: 289, quantity: 2 },
      { id: 'item-103', name: 'Chicken Reshmi Kebab (6 Pcs)', price: 319, quantity: 1 }
    ],
    itemTotal: 897,
    taxesAndFee: 45,
    discount: 100,
    grandTotal: 842,
    paymentMethod: 'UPI (GPay)',
    paymentStatus: 'Paid',
    status: 'On The Way',
    rider: sampleRiders[0],
    placedAt: new Date(Date.now() - 15 * 60 * 1000).toISOString(),
    etaMinutes: 12
  },
  {
    orderId: 'ORD-9820',
    customerName: 'Priya Sharma',
    customerPhone: '+91 97766 55443',
    deliveryAddress: 'Tower 3, Prestige Tech Park, Marathahalli, Bangalore',
    restaurantId: 'rest-2',
    restaurantName: 'Pizza Oven Supreme',
    items: [
      { id: 'item-201', name: 'Pepperoni & Cheese Loaded Pizza (Medium)', price: 399, quantity: 1 },
      { id: 'item-203', name: 'Garlic Breadsticks with Cheese Dip', price: 149, quantity: 1 }
    ],
    itemTotal: 548,
    taxesAndFee: 35,
    discount: 50,
    grandTotal: 533,
    paymentMethod: 'Credit Card',
    paymentStatus: 'Paid',
    status: 'Preparing',
    rider: sampleRiders[1],
    placedAt: new Date(Date.now() - 8 * 60 * 1000).toISOString(),
    etaMinutes: 22
  },
  {
    orderId: 'ORD-9819',
    customerName: 'Rohan Gupta',
    customerPhone: '+91 96543 21098',
    deliveryAddress: '12th Main Road, HSR Layout Sector 4, Bangalore',
    restaurantId: 'rest-4',
    restaurantName: 'Sagar Ratna South Delights',
    items: [
      { id: 'item-401', name: 'Ghee Roast Masala Dosa', price: 139, quantity: 2 },
      { id: 'item-403', name: 'Degree Filter Coffee (200ml)', price: 49, quantity: 2 }
    ],
    itemTotal: 376,
    taxesAndFee: 25,
    discount: 40,
    grandTotal: 361,
    paymentMethod: 'Cash on Delivery',
    paymentStatus: 'Pending',
    status: 'Delivered',
    rider: sampleRiders[2],
    placedAt: new Date(Date.now() - 45 * 60 * 1000).toISOString(),
    etaMinutes: 0
  }
];
