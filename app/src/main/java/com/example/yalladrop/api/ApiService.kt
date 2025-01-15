import com.example.yalladrop.api.auth.User
import com.example.yalladrop.api.Ordering.Order
import com.example.yalladrop.api.Ordering.OrderItem
import com.example.yalladrop.api.meals.Meal
import com.example.yalladrop.api.restauration.Restaurant
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

data class AuthResponse(val message: String = "User created successfully", val token: String? = null , val user : User)

data class SignupRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String,
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class UpdateUserRequest(
    val name: String,
    val phone: String
)

data class VerifyRequest(val token: String)


data class OrderRequest(
    val user: String,
    val restaurant: String,
    val items: List<OrderItem>,
    val totalPrice: Double,
    val deliveryAddress: String,
    val deliveryNotes: String? = null
)

data class OrderResponse(
    val success: Boolean,
    val message: String? = null,
    val order: Order? = null,
    val orders : List<Order> = emptyList()
)

data class OrderStatusUpdateRequest(val status: String)



data class RestaurantResponse(
    val success: Boolean,
    val message: String? = null,
    val restaurant: Restaurant? = null,
    val restaurants: List<Restaurant>? = null
)

data class CreateRestaurantRequest(
    val name: String,
    val logo: String = "",
    val category: String = "",
    val cuisineType: List<String>,
    val averageRating: Double = 0.0,
    val deliveryFee: Double = 0.0,
    val deliveryTime: String = "",
    val discount: Any? = null,
    val reviewCount: Int = 0
)



data class MealRequest(
    val name: String,
    val description: String? = null,
    val price: Double,
    val category: String? = null,
    val calories: Int? = null,
    val ingredients: List<String> = emptyList(),
    val preparationTime: Int? = null,
    val rating: Double? = null,
    val reviews: Int? = null,
    val imageRes: String? = null
)

data class MealResponse(
    val success: Boolean,
    val meal: Meal? = null,
    val error: String? = null ,
    val meals: List<Meal> = emptyList()
)


interface ApiService {
    @POST("api/users")
    suspend fun createUser(@Body request: SignupRequest): AuthResponse

    @POST("api/users/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("api/users/verify")
    suspend fun verifyEmail(@Body request: VerifyRequest): AuthResponse

    @PUT("api/users/{id}")
    suspend fun updateUser(
        @Path("id") id: String,
        @Body user: UpdateUserRequest
    ): AuthResponse

    // Create an order
    @POST("api/orders")
    suspend fun createOrder(@Body request: OrderRequest): OrderResponse

    // Get an order by ID
    @GET("api/orders/{id}")
    suspend fun getOrderById(@Path("id") orderId: String): OrderResponse

    // Update order status
    @PUT("api/orders/{id}/status")
    suspend fun updateOrderStatus(
        @Path("id") orderId: String,
        @Body request: OrderStatusUpdateRequest
    ): OrderResponse

    // Get all orders
    @GET("api/orders")
    suspend fun getAllOrders(): OrderResponse

    // Delete an order
    @DELETE("api/orders/{id}")
    suspend fun deleteOrder(@Path("id") orderId: String): OrderResponse


    // Create a restaurant
    @POST("api/restaurants")
    suspend fun createRestaurant(@Body request: CreateRestaurantRequest): RestaurantResponse

    // Get all restaurants
    @GET("api/restaurants")
    suspend fun getAllRestaurants(): RestaurantResponse

    // Get a restaurant by ID
    @GET("api/restaurants/{id}")
    suspend fun getRestaurantById(@Path("id") restaurantId: String): RestaurantResponse

    // Update a restaurant by ID
    @PUT("api/restaurants/{id}")
    suspend fun updateRestaurant(
        @Path("id") restaurantId: String,
        @Body request: CreateRestaurantRequest
    ): RestaurantResponse

    // Delete a restaurant by ID
    @DELETE("api/restaurants/{id}")
    suspend fun deleteRestaurant(@Path("id") restaurantId: String): RestaurantResponse


    @GET("api/meal/{restaurantId}/meals")
    suspend fun getMealsByRestaurant(
        @Path("restaurantId") restaurantId: String
    ): List<Meal>

    @POST("api/meal/{restaurantId}/meals")
    suspend fun createMeal(
        @Path("restaurantId") restaurantId: String,
        @Body mealRequest: MealRequest
    ): Meal

    @PUT("api/meal/meals/{mealId}")
    suspend fun updateMeal(
        @Path("mealId") mealId: String,
        @Body mealRequest: MealRequest
    ): Meal

    @DELETE("api/meal/meals/{mealId}")
    suspend fun deleteMeal(
        @Path("mealId") mealId: String
    )

    // Get all meals
    @GET("api/meal")
    suspend fun getAllMeals(): MealResponse
}
