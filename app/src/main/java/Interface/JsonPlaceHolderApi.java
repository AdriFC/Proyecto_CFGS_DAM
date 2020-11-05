package Interface;

import java.util.List;

import Modelo.Posts;
import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolderApi {

    //Método para obtener la información de la API, propio de retrofit, devuelve un response
    //de tipo modelo (clase creada, Post) con los datos del modelo

    @GET("posts")                    //Indica la parte de la url de dónde va a coger los datos
    Call<List<Posts>> getPosts ();
}
