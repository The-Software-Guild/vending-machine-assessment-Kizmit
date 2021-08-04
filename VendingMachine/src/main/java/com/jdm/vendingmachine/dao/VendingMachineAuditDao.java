package com.jdm.vendingmachine.dao;

/**
 *
 * @author Joe
 */

public interface VendingMachineAuditDao {

    /**
     *
     * @param entry
     * @throws ItemPersistenceException
     */
    public void writeAuditEntry(String entry) throws ItemPersistenceException;
}
