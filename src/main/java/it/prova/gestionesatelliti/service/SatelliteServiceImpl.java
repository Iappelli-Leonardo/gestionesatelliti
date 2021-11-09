package it.prova.gestionesatelliti.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.prova.gestionesatelliti.model.Satellite;
import it.prova.gestionesatelliti.model.Stato;
import it.prova.gestionesatelliti.repository.SatelliteRepository;

@Service
public class SatelliteServiceImpl implements SatelliteService{

	@Autowired
	SatelliteRepository satelliteRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	@Transactional(readOnly = true)
	public List<Satellite> listAllElements() {
		return (List<Satellite>) satelliteRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Satellite caricaSingoloElemento(Long id) {
		return satelliteRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void aggiorna(Satellite satelliteInstance) {
		satelliteRepository.save(satelliteInstance);
	}

	@Override
	@Transactional
	public void inserisciNuovo(Satellite satelliteInstance) {
		satelliteRepository.save(satelliteInstance);
	}

	@Override
	@Transactional
	public void rimuovi(Satellite satelliteInstance) {
		satelliteRepository.delete(satelliteInstance);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Satellite> findByExample(Satellite example) {
		Map<String, Object> paramaterMap = new HashMap<String, Object>();
		List<String> whereClauses = new ArrayList<String>();

		StringBuilder queryBuilder = new StringBuilder("select s from Satellite s where s.id = s.id ");

		if (StringUtils.isNotEmpty(example.getCodice())) {
			whereClauses.add(" s.codice  like :codice ");
			paramaterMap.put("codice", "%" + example.getCodice() + "%");
		}
		if (StringUtils.isNotEmpty(example.getDenominazione())) {
			whereClauses.add(" s.denominazione like :denominazione ");
			paramaterMap.put("denominazione", "%" + example.getDenominazione() + "%");
		}
		if (example.getDataLancio() != null) {
			whereClauses.add("s.dataLancio >= :dataLancio ");
			paramaterMap.put("dataLancio", example.getDataLancio());
		}
		if (example.getDataLancio() != null) {
			whereClauses.add("s.dataRientro >= :dataRientro ");
			paramaterMap.put("dataRientro", example.getDataLancio());
		}
	
		if (example.getStato() != null) {
			whereClauses.add(" s.stato =:stato ");
			paramaterMap.put("stato", example.getStato());
		}
		
		queryBuilder.append(!whereClauses.isEmpty()?" and ":"");
		queryBuilder.append(StringUtils.join(whereClauses, " and "));
		TypedQuery<Satellite> typedQuery = entityManager.createQuery(queryBuilder.toString(), Satellite.class);

		for (String key : paramaterMap.keySet()) {
			typedQuery.setParameter(key, paramaterMap.get(key));
		}

		return typedQuery.getResultList();
	}
	
	@Transactional(readOnly = true)
	public List<Satellite> findAllAnnniDisattivati() {
		Date date = new Date();
		date.setYear(date.getYear()-2);
		System.out.println(date);
		return satelliteRepository.findAllByDataLancioLessThanAndStatoNot(date, Stato.DISATTIVATO);
	}

	@Transactional(readOnly = true)
	public List<Satellite> findDisattivatiNonRientrati() {
		return satelliteRepository.findAllByStatoEqualsAndDataRientroEquals(Stato.DISATTIVATO, null);
	}
	
	
	@Transactional(readOnly = true)
	public List<Satellite> findPiuDiDieciAnniFissi() {
		Date date = new Date();
		date.setYear(date.getYear()-10);
		System.out.println(date);
		return satelliteRepository.findAllByDataLancioLessThanAndStatoEquals(date, Stato.FISSO);
	}


}
