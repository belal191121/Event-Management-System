/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author pc
 */
public class ClientFacade extends UnicastRemoteObject implements ReserveeInterface {

    Reservee r;
    Visitor V;
    Admin a;
    public ClientFacade() throws RemoteException {
        r = new Reservee("", "", "");
        V = new Visitor("", "", "");
    }

    @Override
    public ArrayList<Integer> getEventsID() throws RemoteException {

        return r.getUpcomingEvents();
    }

    @Override
    public String getEventName(int ID) throws RemoteException {
        DB db = DB.getinstance();
        Event a = db.findEventByID(ID);
        return a.getEventName();

    }

    @Override
    public Date GetEventDate(int ID) throws RemoteException {
        DB db = DB.getinstance();
        Event a = db.findEventByID(ID);
        return a.getEventDate();
    }

    @Override
    public String getVenueName(int iD) throws RemoteException {
        DB db = DB.getinstance();
        Event a = db.findEventByID(iD);
        return a.getEvetVenue().getVenueName();
    }

    @Override
    public ArrayList<String> getvisitors(int ID) throws RemoteException {
        DB db = DB.getinstance();
        Event a = db.findEventByID(ID);
        ArrayList<String> n = new ArrayList<>();
        for (int i = 0; i < a.getVisitors().size(); i++) {
            n.add(a.getVisitors().get(i).getEmail_Address());
        }
        return n;
    }

    @Override
    public Void KickVisior(String Email, int ID) throws RemoteException {
        DB db = DB.getinstance();
        Event a = db.findEventByID(ID);
        for (int i = 0; i < a.getVisitors().size(); i++) {
            if (a.getVisitors().get(i).getName().equals(Email)) {
                a.getVisitors().remove(i);
            }

        }
        return null;
    }

    @Override
    public String SendAnnouncment(int ID, String Announcment) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<Integer> getHostedEventIDs(String Email) throws RemoteException {
        return r.getHostedEvents();
    }

    @Override
    public ArrayList<String> GetEventFeedback(int ID) throws RemoteException {
           DB db = DB.getinstance();
        Event a = db.findEventByID(ID);
        ArrayList<String> n = new ArrayList<>();
        for (int i = 0; i < a.getFeedback().size(); i++) {
            n.add(a.getFeedback().get(i).getFeedback());
        }
        return n;
    }

    @Override
    public void Login(String Email, String Password) throws RemoteException {
        DB db = DB.getinstance();
        r = db.LoginReservee(Email, Password);
        V = db.loginVisitor(Email, Password);
        System.out.println(r.getUpcomingEvents());

    }

    @Override
    public String Test() throws RemoteException {
        String s="test";
        return s;
    }

    @Override
    public ArrayList<String> getVenues() throws RemoteException {
        ArrayList<String> lst = new ArrayList<>();
        for(Venue v: Admin.getOneAdmin().getVenues()){
            lst.add(v.getVenueName());
        }
        return lst;
    }

    @Override
    public ArrayList<String> getCompanies() throws RemoteException {
        ArrayList<String> lst = new ArrayList<>();
        for(ThirdPartyCompany c: Admin.getOneAdmin().getThirdPartyCompanies()){
            lst.add(c.getName());
        }
        return lst;
    }

   
    
    @Override
    public int findIndexOfVenueByname(String N) throws RemoteException {
        for (int i = 0; i < a.getVenues().size(); i++) {
            String vName = a.getVenues().get(i).getVenueName();
            if (N.equals(vName)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public void reserveEvent(String vName, ArrayList<String> companies,String bank, int cardNo, int CCV, boolean isPublic, Date date) throws RemoteException {
        Venue v = a.getVenues().get(findIndexOfVenueByname(vName));
        PaymentMethod p = new PaymentMethod(cardNo,CCV,bank);
        Event_Request e = new Event_Request(v,"Pending","",companies,p,(int)Math.random()*100,isPublic,date);
        Admin.getOneAdmin().addEventRequests(e);
        DB db = DB.getinstance();
        db.UpdateAdmin();
    }
}
