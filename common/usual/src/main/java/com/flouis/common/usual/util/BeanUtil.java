package com.flouis.common.usual.util;

import com.google.common.collect.Maps;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class BeanUtil {

	/**
	 * @description source中的非空属性复制到target中
	 */
	public static <T> void beanCopy(T source, T target) {
		BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
	}

	/**
	 * @description source中的非空属性复制到target中，但是忽略指定的属性，也就是说有些属性是不可修改的（个人业务需要）
	 */
	public static <T> void beanCopyWithIngore(T source, T target, String... ignoreProperties) {
		String[] pns = getNullAndIgnorePropertyNames(source, ignoreProperties);
		BeanUtils.copyProperties(source, target, pns);
	}

	public static String[] getNullAndIgnorePropertyNames(Object source, String... ignoreProperties) {
		Set<String> emptyNames = getNullPropertyNameSet(source);
		for (String s : ignoreProperties) {
			emptyNames.add(s);
		}
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static String[] getNullPropertyNames(Object source) {
		Set<String> emptyNames = getNullPropertyNameSet(source);
		String[] result = new String[emptyNames.size()];
		return emptyNames.toArray(result);
	}

	public static Set<String> getNullPropertyNameSet(Object source) {
		final BeanWrapper src = new BeanWrapperImpl(source);
		PropertyDescriptor[] pds = src.getPropertyDescriptors();
		Set<String> emptyNames = new HashSet<>();
		for (PropertyDescriptor pd : pds) {
			Object srcValue = src.getPropertyValue(pd.getName());
			if (srcValue == null) emptyNames.add(pd.getName());
		}
		return emptyNames;
	}

	/**
	 * @description Bean->Map
	 */
	public static Map<String, Object> beanToMap(Object object) {
		Map<String, Object> map = Maps.newHashMap();
		for (Field field : object.getClass().getDeclaredFields()){
			try {
				boolean flag = field.isAccessible();
				field.setAccessible(true);
				Object o = field.get(object);
				map.put(field.getName(), o);
				field.setAccessible(flag);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return map;
	}

	/**
	 * @description Map->Bean
	 */
	public static <T> T mapToBean(Map<String, Object> map, Class<T> entity) {
		T t = null;
		try {
			t = entity.newInstance();
			for(Field field : entity.getDeclaredFields()) {
				if (map.containsKey(field.getName())) {
					boolean flag = field.isAccessible();
					field.setAccessible(true);
					Object object = map.get(field.getName());
					if (object!= null && field.getType().isAssignableFrom(object.getClass())) {
						field.set(t, object);
					}
					field.setAccessible(flag);
				}
			}
			return t;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return t;
	}

}
