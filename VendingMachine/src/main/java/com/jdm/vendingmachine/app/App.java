package com.jdm.vendingmachine.app;

import com.jdm.vendingmachine.controller.VendingMachineController;
import com.jdm.vendingmachine.dao.VendingMachineAuditDao;
import com.jdm.vendingmachine.dao.VendingMachineAuditDaoImpl;
import com.jdm.vendingmachine.dao.VendingMachineDao;
import com.jdm.vendingmachine.dao.VendingMachineDaoFileImpl;
import com.jdm.vendingmachine.service.VendingMachineServiceLayer;
import com.jdm.vendingmachine.service.VendingMachineServiceLayerImpl;
import com.jdm.vendingmachine.ui.UserIO;
import com.jdm.vendingmachine.ui.UserIOConsoleImpl;
import com.jdm.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author Joe McAdams
 * @email joedmcadams@gmail.com
 * 
 */
public class App {
    public static void main(String[] args){
        UserIO io = new UserIOConsoleImpl();
        VendingMachineView view = new VendingMachineView(io);
        VendingMachineDao dao = new VendingMachineDaoFileImpl();
        VendingMachineAuditDao auditDao = new VendingMachineAuditDaoImpl();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao, auditDao);
        VendingMachineController controller = new VendingMachineController(service, view);
        controller.run();
    }
}
