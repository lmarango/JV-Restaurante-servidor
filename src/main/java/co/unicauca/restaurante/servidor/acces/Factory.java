
package co.unicauca.restaurante.servidor.acces;

import co.unicauca.restaurante.comunicacion.infra.Utilities;

/**
 *
 * @author Luis Arango
 */
public class Factory {
    private static Factory instance;
    private Factory(){}
    public static Factory getInstance(){
        if (instance == null) {
            instance = new Factory();
        }
        return instance;
    }
    public IRestaurantRepository getRepository(){
        String type = Utilities.loadProperty("restaurant.repository");
        if(type.isEmpty()){
            type = "mysql";
        }
        IRestaurantRepository result = null;
        
        switch (type){
            case "mysql" -> result = new RestaurantRepositoryImplMysql();
        }
        return result;
    }
    public IUserRepository getRepositoryUser(){
        String type = Utilities.loadProperty("restaurant.repository");
        if (type.isEmpty()) {
            type = "mysql";
        }
        IUserRepository result = null;
        switch (type) {
            case "mysql" -> result = new UserRepositoryImplMysql();
        }
        return result;
    }
    public IDishRepository getRepositoryDish() {
        String type = Utilities.loadProperty("restaurant.repository");
        if (type.isEmpty()) {
            type = "mysql";
        }
        IDishRepository result = null;
        switch (type) {
            case "mysql":
                result = new DishRepositoryImplMysql();
                break;
        }
        return result;
    }
}
