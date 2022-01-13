package net.manage_employee.registration.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import net.manage_employee.registration.DBUtil.HibernateUtile;
import net.manage_employee.registration.model.Employee;

public class EmployeeDao {
	
	// save Employee
	// get All employee
	// get employee By Id
	// Update employee
	// Delete employee
	
	public void saveEmployee(Employee employee) {
		Transaction transaction = null;
		try(Session session = HibernateUtile.getSessionFactory().openSession()){
			// start the transaction
			transaction = session.beginTransaction();
			
			// save employee
			session.save(employee);
			
			// commit the transaction
			transaction.commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	}
	
	public void updateEmployee(Employee employee) {
		Transaction transaction = null;
		try(Session session = HibernateUtile.getSessionFactory().openSession()){
			// start the transaction
			transaction = session.beginTransaction();
			
			// update employee
			session.saveOrUpdate(employee);
			
			// commit the transaction
			transaction.commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		
	} 
	
	public Employee getEmployeeById(Long id) {
		Transaction transaction = null;
		Employee employee = null;
		try(Session session = HibernateUtile.getSessionFactory().openSession()){
			// start the transaction
			transaction = session.beginTransaction();
			
			employee = session.get(Employee.class, id);
			// we also can use load() method
			// employee = session.load(Employee.class, id)
			
			// commit the transaction
			transaction.commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		return employee;
	}
	
	@SuppressWarnings("unchecked")
	public List<Employee> getEmployees() {
		Transaction transaction = null;
		List<Employee> employees = null; 
		//Employee employee = null;
		try(Session session = HibernateUtile.getSessionFactory().openSession()){
			// start the transaction
			transaction = session.beginTransaction();
			
			employees = session.createQuery("from Employee").list();
			// we also can use load() method
			// employee = session.load(Employee.class, id)
			
			// commit the transaction
			transaction.commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		return employees;
	}
	
	public Employee deleteEmployee(Long id) {
		Transaction transaction = null;
		Employee employee = null;
		try(Session session = HibernateUtile.getSessionFactory().openSession()){
			// start the transaction
			transaction = session.beginTransaction();
			employee = session.get(Employee.class, id);
			session.delete(employee);
			
			// commit the transaction
			transaction.commit();
		} catch(Exception e) {
			if(transaction != null) {
				transaction.rollback();
			}
		}
		return employee;
	}
	
	

}

