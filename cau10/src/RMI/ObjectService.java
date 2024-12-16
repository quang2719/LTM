/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMI;
import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ObjectService extends Remote {
    public Serializable requestObject(String studentCode, String qCode) throws RemoteException;
    public void submitObject(String studentCode, String qCode, Serializable object) throws RemoteException;
}