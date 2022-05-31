package id.phephen.al_islamic_apps.helper

/**
 * Created by Phephen on 31/05/2022.
 */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Loading<T> : Resource<T>()
    class Success<T>(data: T): Resource<T>( data )
    class Error<T>(message: String, data: T? = null): Resource<T>( data, message )
}
