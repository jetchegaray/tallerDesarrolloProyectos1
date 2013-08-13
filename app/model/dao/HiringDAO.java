package model.dao;

import java.util.List;

import model.domain.Event;
import model.domain.Hiring;
import model.domain.Task;
import model.domain.Wedding;

import org.bson.types.ObjectId;

import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;
import com.google.code.morphia.query.QueryResults;
import com.google.common.collect.Lists;

public class HiringDAO extends BasicDAO<Hiring,ObjectId>{

	private static HiringDAO instance = new HiringDAO();

	private HiringDAO() {
		super(DaoConfiguration.mongo, DaoConfiguration.morphia, DaoConfiguration.dBAddress.getDatabase());
	}

	public static HiringDAO getHiringDAO(){
		return HiringDAO.instance;
	}

	public List<Hiring> listHirings(Wedding wedding) {
		return hiringsForWedding(wedding).asList();
	}

	public List<Hiring> listHirings(Wedding wedding, Event event, Task task) {
		Query<Hiring> query = createQuery();
		query.field("wedding").equal(wedding);
		query.field("eventType").equal(event.getTypeName());
		query.field("taskSlug").equal(task.slug);

		return query.asList();
	}

	public QueryResults<Hiring> hiringsForWedding(Wedding wedding) {
		Query<Hiring> query = createQuery();
		query.field("wedding").equal(wedding);

		return find(query);
	}

}
