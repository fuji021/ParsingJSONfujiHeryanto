package id.ac.pelitabangsa.parsingjsonfujiheryanto.network

interface ApiService {
    //Menampilkan user dengan query
    @GET("api/user")
    fun getListUsers(@Query("page")page: String): Call<ResponUser>
}