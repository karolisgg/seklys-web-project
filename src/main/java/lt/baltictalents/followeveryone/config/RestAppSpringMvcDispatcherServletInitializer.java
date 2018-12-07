package lt.baltictalents.followeveryone.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class RestAppSpringMvcDispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

   @Override
   protected Class<?>[] getRootConfigClasses() {
      return new Class[0];
   }

   @Override
   protected Class<?>[] getServletConfigClasses() {
      return new Class[] {RestAppConfig.class};
   }

   @Override
   protected String[] getServletMappings() {
      return new String[] {"/"};
   }
}
