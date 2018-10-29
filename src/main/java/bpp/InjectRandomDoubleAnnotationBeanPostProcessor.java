package bpp;

import annotation.InjectRandomDouble;
import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.util.ReflectionUtils;

public class InjectRandomDoubleAnnotationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
        throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            InjectRandomDouble annotation = field.getAnnotation(InjectRandomDouble.class);
            if (annotation != null) {
                double value = ThreadLocalRandom.current().nextDouble(annotation.min(), annotation.max());
                field.setAccessible(true);
                ReflectionUtils.setField(field, bean, value);
            }
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
        throws BeansException {
        return bean;
    }
}
