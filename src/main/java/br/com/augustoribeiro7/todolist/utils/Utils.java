/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.augustoribeiro7.todolist.utils;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

/**
 *
 * @author gutom
 */
public class Utils {
    //ATUALIZAR ATRIBUTOS ESPECIFICOS DAS TASKS SEM DEIXAR OUTROS ATRIBUTOS NULLS
    
    public static void copyNonNullProperties(Object source, Object target)
    {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }
    
    public static String[] getNullPropertyNames(Object source)
    {
        //acessar propriedades de um objeto
        final BeanWrapper src = new BeanWrapperImpl(source);
        
        //obter propriedades
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        
        Set<String> emptyNames = new HashSet<>();
        
        for(PropertyDescriptor pd:pds)
        {
            Object srcValue = src.getPropertyValue(pd.getName());
            if(srcValue == null)
            {
                emptyNames.add(pd.getName());
            }
        }
        
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
