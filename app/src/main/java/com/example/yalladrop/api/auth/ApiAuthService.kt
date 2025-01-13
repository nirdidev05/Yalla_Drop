import android.provider.ContactsContract.CommonDataKinds.Phone
import retrofit2.http.Body
import retrofit2.http.POST

data class AuthResponse(val message: String = "User created successfully", val token: String? = null)

data class SignupRequest(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)

data class LoginRequest(
    val email: String,
    val password: String
)

data class VerifyRequest(val email: String, val code: String)

interface ApiService {
    @POST("api/users")
    suspend fun createUser(@Body request: SignupRequest): AuthResponse

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("api/users/verify")
    suspend fun verifyEmail(@Body request: VerifyRequest): AuthResponse
}
