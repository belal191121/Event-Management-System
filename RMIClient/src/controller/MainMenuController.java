/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import rmi.DBinterface;
import rmi.newLogin ;
import rmi.UserMenu;
/**
/**
 *
 * @author pc
 */
public class MainMenuController {
    public UserMenu gui;
   public Registry r;
   public String email;
   public String Password;

    public MainMenuController(UserMenu gui, Registry r,String Email,String password) {
        this.gui = gui;
        this.r = r;
        // This registers the button with our action listener below (the inner class)
        gui.getjButton1().addActionListener(new ReserveEvent());
        gui.getjButton5().addActionListener(new ViewEvent());
        gui.getjButton2().addActionListener(new sendInvite());
        gui.getjButton3().addActionListener(new ViewInvites());
        gui.getjButton4().addActionListener(new ViewNotificatios());
        gui.getjButton6().addActionListener(new Logout(gui,r));
    }

  

    private static class ReserveEvent implements ActionListener {

       

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }

    private static class ViewEvent implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

      
    }

    private static class sendInvite implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        
    }

    private static class ViewInvites implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    private static class ViewNotificatios implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

 
    }

    private static class Logout implements ActionListener {
         public UserMenu gui;
         public Registry r;
         public  Logout(UserMenu gui,Registry r){
         this.gui=gui;
         this.r=r;
         }
        @Override
        public void actionPerformed(ActionEvent ae) {
            gui.dispose();
            newLogin n=new newLogin();
            n.show();
            LoginController a=new LoginController(n, r);

        }

    
    }
}