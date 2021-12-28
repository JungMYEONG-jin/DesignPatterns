package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class beandefinitionTest {

     AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

     @Test
     @DisplayName("TEST1")
    void findApplcationBean()
     {
         String[] beanNames = ac.getBeanDefinitionNames();
         for(String bname : beanNames)
         {
             BeanDefinition beanDefinition = ac.getBeanDefinition(bname);

             if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION)
             {
                 System.out.println("beanDefinition Name: "+bname+" "+"BeanDefinition = "+beanDefinition);
             }



         }
     }



}
