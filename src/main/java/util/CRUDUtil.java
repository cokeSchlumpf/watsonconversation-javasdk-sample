package util;

import java.lang.reflect.Field;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.google.common.collect.Lists;

import exceptions.ServiceError;
import exceptions.WebApplicationException;

@Stateless
public class CRUDUtil {
	
	@PersistenceContext
	EntityManager em;
	
	/**
	 * Creates a new instance with a newly assigned techical ID.
	 * 
	 * @param in
	 *            The new entity.
	 * @return The new entity with a new ID.
	 */
	public <T extends AbstractPersistedObject> T create(T in) {
		// TODO: Check if there's no id assigned to the incoming entities.
		em.persist(in);
		return in;
	}
	
	/**
	 * Creates multiple new instances with a newly assigned technical ID.
	 * 
	 * @param in
	 *            The list of new entities.
	 * @return The list of all entities with an assigned Id.
	 */
	public <T extends AbstractPersistedObject> List<T> create(List<T> in) {
		List<T> result = Lists.newArrayList();

		for (T entity : in) {
			result.add(entity);
		}

		return result;
	}
	
	/**
	 * Returns a list of all entities with only the fields filled whcih have an
	 * {@link XmlListAllAttribute} annotation.
	 * 
	 * @return A list of all entities in the database.
	 */
	public <T extends AbstractPersistedObject>  List<T> getAll(Class<T> entityClass) {
		CriteriaQuery<Tuple> criteria = em.getCriteriaBuilder().createTupleQuery();
		Root<T> root = criteria.from(entityClass);
		List<Selection<?>> selection = Lists.newArrayList();
		List<T> result = Lists.newArrayList();

		for (Field field : FieldUtils.getFieldsWithAnnotation(entityClass, XmlListAllAttribute.class)) {
			selection.add(root.get(field.getName()).alias(field.getName()));
		}

		criteria.multiselect(selection);

		for (Tuple t : em.createQuery(criteria).getResultList()) {
			try {
				T entity = entityClass.newInstance();
				for (Field field : FieldUtils.getFieldsWithAnnotation(entityClass, XmlListAllAttribute.class)) {
					FieldUtils.writeField(field, entity, t.get(field.getName()), true);
				}
				result.add(entity);
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return result;
	}
	

	public <T extends AbstractPersistedObject> T get(Class<T> clazz, Long id) {
		return getById(clazz, id);
	}
	
	public <T extends AbstractPersistedObject> T update(Long id, T entity) {
		try {
			FieldUtils.writeField(entity, "id", id, true);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		em.persist(entity);
		return entity;
	}
	
	public <T extends AbstractPersistedObject> T delete(Class<T> clazz, Long id) {
		T entity = getById(clazz, id);
		em.remove(entity);
		return entity;
	}
	
	protected <T extends AbstractPersistedObject> T getById(Class<T> clazz, Long id) {
		T entity = em.find(clazz, id);

		if (entity == null) {
			ServiceError error = ServiceError.EntityNotFoundException(clazz.getSimpleName(), id);
			throw new WebApplicationException(error);
		}

		return entity;
	}

}
