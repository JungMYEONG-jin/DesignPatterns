package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("all bean show")
    void findAllBean()
    {
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String name : beanNames)
        {
            Object bean = ac.getBean(name);
            System.out.println("name: "+name+" bean: "+bean);
        }
    }


    @Test
    @DisplayName("application bean show")
    void findAppBean()
    {
        String[] beanNames = ac.getBeanDefinitionNames();
        for(String name : beanNames)
        {
            BeanDefinition bean = ac.getBeanDefinition(name);
            if(bean.getRole() == BeanDefinition.ROLE_APPLICATION)
            {
                Object obj = ac.getBean(name);
                System.out.println("name: "+name+" bean: "+obj);
            }



        }
    }

}
