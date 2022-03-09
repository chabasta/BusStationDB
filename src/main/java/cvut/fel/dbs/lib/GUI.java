package cvut.fel.dbs.lib;

import cvut.fel.dbs.lib.dao.BusEntityDao;
import cvut.fel.dbs.lib.dao.DriverEntityDao;
import cvut.fel.dbs.lib.dao.DrivesEntityDao;
import cvut.fel.dbs.lib.model.BusEntity;
import cvut.fel.dbs.lib.model.DriverEntity;
import cvut.fel.dbs.lib.model.DrivesEntity;
import cvut.fel.dbs.lib.service.BusService;
import cvut.fel.dbs.lib.service.DriverService;
import cvut.fel.dbs.lib.service.DrivesService;
//import org.eclipse.persistence.jpa.config.Table;
//import sun.rmi.runtime.Log;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
//import javax.swing.table.TableColumn;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.util.List;

public class GUI extends JFrame{
    private JPanel mainPanel;

    JTable table1;
    JTable table2;
    JTable table3;

    private JTextField car_number;
    private JTextField brand;
    private JTextField years_in_use;
    private JTextField size;
    private JTextField number;
    private JTextField cer_number_rel;
    private JTextField driver_licence_number_rel;

    private JButton CreateBus;
    private JButton DeleteBus;

    private JButton CreateRealtionship;
    private JButton DeleteRealtionship;
    private JButton UpdateBus;
    private JButton Exit;
    private JButton UpdateRelation;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("pu");
    EntityManager em = emf.createEntityManager();

    BusEntityDao busEntityDao = new BusEntityDao(em);
    BusService busService = new BusService(busEntityDao);

    DrivesEntityDao drivesEntityDao = new DrivesEntityDao(em);
    DrivesService drivesService = new DrivesService(drivesEntityDao);

    DriverEntityDao driverEntityDao = new DriverEntityDao(em);
    DriverService driverService = new DriverService(driverEntityDao);

    public void updateTables(){

        String[] columnsBus = {"car_number","number","size","years_in_use","brand"};
        table1.setModel(new DefaultTableModel(busService.allBusesToRows(),columnsBus));

        String[] columnsDrives = {"car_number","driver_licence_number"};
        table3.setModel(new DefaultTableModel(drivesService.AlltoRows(),columnsDrives));

        String[] columnsDriver = {"name","surname","director_name","director_surname","driver_licence_number"};
        table2.setModel(new DefaultTableModel(driverService.allToRows(),columnsDriver));

    }

    public GUI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();

        updateTables();

        CreateBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BusEntity b = new BusEntity(car_number.getText(),number.getText(),size.getText(),years_in_use.getText(),brand.getText());
                try {
                    busService.create(b);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
                updateTables();
            }
        });

        DeleteBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BusEntity b = null;
                try {
                    b = busService.find(car_number.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
                try {
                    busService.delete(b);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
                updateTables();
            }
        });

        UpdateBus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                BusEntity b = null;
                try {
                    b = busService.find(car_number.getText());
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
                b.setBrand(brand.getText());
                b.setNumber(number.getText());
                b.setSize(size.getText());
                b.setYears_in_use(years_in_use.getText());
                try {
                    busService.merge(b);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
                updateTables();
            }
        });

        CreateRealtionship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrivesEntity b = new DrivesEntity(driver_licence_number_rel.getText(), cer_number_rel.getText());
                try {
                    drivesService.create(b);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
                updateTables();
            }
        });

        DeleteRealtionship.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    drivesService.delete(drivesService.find(driver_licence_number_rel.getText()));
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
                updateTables();
            }
        });

        UpdateRelation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrivesEntity p = new DrivesEntity(driver_licence_number_rel.getText(),cer_number_rel.getText());
                try {
                    drivesService.merge(p);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
            }
        });

        UpdateRelation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DrivesEntity p = new DrivesEntity(driver_licence_number_rel.getText(),cer_number_rel.getText());
                try {
                    drivesService.merge(p);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(new JFrame(), exception);
                }
            }
        });

        Exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String[] args) {
        JFrame frame = new GUI("DB");
        frame.setVisible(true);
    }
}
