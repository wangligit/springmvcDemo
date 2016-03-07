package com.lora.dao;

import java.util.List;

import com.lora.model.PersonBean;

public interface PersonDao {

    public void addPerson(PersonBean person); 
    
    public void updatePerson(PersonBean person); 
      
    public void deletePerson(int id); 
      
    public PersonBean queryPerson(int id); 
      
    public List<PersonBean> queryPersons(); 
}
