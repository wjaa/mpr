package br.com.wjaa.mpr.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.com.wjaa.mpr.entity.Configuration;

@Repository
public class ConfigurationDAOImpl extends GenericDaoImpl<Configuration, Integer> implements ConfigurationDAO{

	public ConfigurationDAOImpl() {
		super(Configuration.class);
	}

	@Override
	public Configuration save(Configuration c) {
		Configuration config = c;
		List<Configuration> configs = this.getAll();
		if (configs.size() > 0){
			config = this.getAll().get(0);
			if (config != null){
				config.setPathImgPr(c.getPathImgPr());
				config.setPathUpload(c.getPathUpload());
				config.setNumParcela(c.getNumParcela());
				config.setLigaGoogleAnalytics(c.getLigaGoogleAnalytics());
				config.setLigaDesconto(c.getLigaDesconto());
				config.setPorcentDesconto(c.getPorcentDesconto());
			}
		}
		return super.save(config);
	}

}
