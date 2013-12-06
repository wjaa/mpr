package br.com.wjaa.mpr.dao;

import org.springframework.stereotype.Repository;

import br.com.wjaa.mpr.entity.Configuration;

@Repository
public class ConfigurationDAOImpl extends GenericDaoImpl<Configuration, Integer> implements ConfigurationDAO{

	public ConfigurationDAOImpl() {
		super(Configuration.class);
	}

	@Override
	public Configuration save(Configuration c) {
		Configuration config = this.getAll().get(0);
		if (config != null){
			config.setPathImgPr(c.getPathImgPr());
			config.setPathUpload(c.getPathUpload());
		}
		return super.save(config);
	}

}
