
package co.unicauca.restaurante.servidor.acces;

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
}
