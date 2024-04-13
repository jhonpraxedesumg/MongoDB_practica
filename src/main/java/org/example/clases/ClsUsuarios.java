package org.example.clases;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.List;

public class ClsUsuarios {
    private MongoClient mongoClient;
    private MongoDatabase database;
    private MongoCollection <Document> collection;

    public void conexion (){
        //cadena de conexion, contiene la información de la insalacion del mongodb
        ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017");

        //se crean las configuraciones especificas para conexion y manejo de la base de datos
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .build();
        mongoClient = MongoClients.create(settings);
        database = mongoClient.getDatabase("miBasedatos");
        collection = database.getCollection("usuarios");
        System.out.println("conexion realizada");

    }

    public void crearUsuario(){
        mdUsuario usuario= new mdUsuario();
        usuario.setNombre("Monica");
        usuario.setCorreo("Monica@example.com");
        usuario.setIdTelegram(123456789);

        Document  document = new Document("Nombre",usuario.getCorreo())
                .append("correo",usuario.getCorreo())
                .append("IdTelegram",usuario.getIdTelegram());
        collection.insertOne(document);
        System.out.println("Usuario Creado");
    }
    public void leerUsuario(){
        List<mdUsuario>usuarios=new ArrayList<>();
        for (Document doc: collection.find()){
            mdUsuario u = new mdUsuario();
            u.setIdTelegram(doc.getLong("idTelegram"));
            u.setNombre(doc.getString("nombre"));
            u.setCorreo(doc.getString("correo"));
            usuarios.add(u);
        }
        for (mdUsuario u: usuarios){
            System.out.println("usuario:"+u.getNombre()+"correo:"= u.getCorreo());
        }
    }
    private  void actualizarUsuario() {
        collection.updateOne(Filters.eq("idTelegram", 88745587),
                Updates.set("correo", "Nuevo@example.com"));
        System.out.println("Usuario actualizado");
    }

    private  void eliminarUsuario() {
        collection.deleteOne(Filters.eq("idTelegram", 9995587));
        System.out.println("Usuario eliminado");
    }

    private  void desconectarMongoDB() {
        // Cerrar la conexión
        mongoClient.close();
        System.out.println("Desconectado de MongoDB");
    }
}
