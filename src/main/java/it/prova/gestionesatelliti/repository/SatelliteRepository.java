package it.prova.gestionesatelliti.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.prova.gestionesatelliti.model.Satellite;
import it.prova.gestionesatelliti.model.Stato;


public interface SatelliteRepository  extends CrudRepository<Satellite, Long> {

	List<Satellite> findAllByDataLancioLessThanAndStatoNot(Date data, Stato stato);
	
	List<Satellite> findAllByStatoEqualsAndDataRientroEquals(Stato stato, Date data);
	
	List<Satellite> findAllByDataLancioLessThanAndStatoEquals(Date data, Stato stato);
}
