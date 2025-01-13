const Restaurant = require('../models/Restaurant');

// Create a new restaurant
exports.createRestaurant = async (req, res) => {
  const { name, logo, location, cuisineType, averageRating, reviewCount, contact } = req.body;
  try {
    const newRestaurant = new Restaurant({ name, logo, location, cuisineType, averageRating, reviewCount, contact });
    await newRestaurant.save();
    res.status(201).json({ success: true, restaurant: newRestaurant });
  } catch (error) {
    res.status(500).json({ success: false, message: 'Failed to create restaurant', error });
  }
};

// Get a restaurant by ID
exports.getRestaurantById = async (req, res) => {
  try {
    const restaurant = await Restaurant.findById(req.params.id);
    if (!restaurant) {
      return res.status(404).json({ success: false, message: 'Restaurant not found' });
    }
    res.status(200).json({ success: true, restaurant });
  } catch (error) {
    res.status(500).json({ success: false, message: 'Failed to fetch restaurant', error });
  }
};

// Update restaurant info
exports.updateRestaurant = async (req, res) => {
  try {
    const updatedRestaurant = await Restaurant.findByIdAndUpdate(req.params.id, req.body, { new: true });
    if (!updatedRestaurant) {
      return res.status(404).json({ success: false, message: 'Restaurant not found' });
    }
    res.status(200).json({ success: true, restaurant: updatedRestaurant });
  } catch (error) {
    res.status(500).json({ success: false, message: 'Failed to update restaurant', error });
  }
};

// Delete restaurant
exports.deleteRestaurant = async (req, res) => {
  try {
    const deletedRestaurant = await Restaurant.findByIdAndDelete(req.params.id);
    if (!deletedRestaurant) {
      return res.status(404).json({ success: false, message: 'Restaurant not found' });
    }
    res.status(200).json({ success: true, message: 'Restaurant deleted successfully' });
  } catch (error) {
    res.status(500).json({ success: false, message: 'Failed to delete restaurant', error });
  }
};
