import android.provider.ContactsContract.CommonDataKinds.Phone
import com.example.yalladrop.api.auth.User
import retrofit2.http.Body
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
}
