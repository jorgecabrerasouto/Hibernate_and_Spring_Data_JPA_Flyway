package co.com.jorgecabrerasouto.sdjpa.creditcard.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import co.com.jorgecabrerasouto.sdjpa.creditcard.listeners.PostLoadListener;
import co.com.jorgecabrerasouto.sdjpa.creditcard.listeners.PreInsertListener;
import co.com.jorgecabrerasouto.sdjpa.creditcard.listeners.PreUpdateListener;

//@Component
public class ListnerRegistration implements BeanPostProcessor {
	
	private final PostLoadListener postLoadListener;
	private final PreInsertListener preInsertListener;
	private final PreUpdateListener preUpdateListener;
	
	
	public ListnerRegistration(PostLoadListener postLoadListener, PreInsertListener preInsertListener,
			PreUpdateListener preUpdateListener) {
		this.postLoadListener = postLoadListener;
		this.preInsertListener = preInsertListener;
		this.preUpdateListener = preUpdateListener;
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
		return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
//		 if (bean instanceof LocalContainerEntityManagerFactoryBean){
//	            LocalContainerEntityManagerFactoryBean lemf = (LocalContainerEntityManagerFactoryBean) bean;
//	            SessionFactoryImpl sessionFactory = (SessionFactoryImpl) lemf.getNativeEntityManagerFactory();
//	            EventListenerRegistry registry = sessionFactory.getServiceRegistry()
//	                    .getService(EventListenerRegistry.class);
//
//	            registry.appendListeners(EventType.POST_LOAD, postLoadListener);
//	            registry.appendListeners(EventType.PRE_INSERT, preInsertListener);
//	            registry.appendListeners(EventType.PRE_UPDATE, preUpdateListener);
//	        }
		return bean;
	}

}
