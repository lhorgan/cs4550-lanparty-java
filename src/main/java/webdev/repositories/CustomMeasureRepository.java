//package webdev.repositories;
//
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Repository;
//
//import webdev.models.Measure;
//
//@Repository("customMeasureRepository")
//public class CustomMeasureRepository implements MeasureRepository {
//	@Autowired
//    @Qualifier("measureRepository") // inject Spring implementation here
//    private MeasureRepository measureRepository;
//	
//	@Override
//	public long count() {
//		// TODO Auto-generated method stub
//		return measureRepository.count();
//	}
//
//	@Override
//	public void delete(Measure measure) {
//		measureRepository.delete(measure);
//	}
//
//	@Override
//	public void deleteAll() {
//		measureRepository.deleteAll();
//	}
//
//	@Override
//	public void deleteAll(Iterable<? extends Measure> measures) {
//		measureRepository.deleteAll(measures);
//	}
//
//	@Override
//	public void deleteById(Integer id) {
//		measureRepository.deleteById(id);
//	}
//
//	@Override
//	public boolean existsById(Integer id) {
//		return measureRepository.existsById(id);
//	}
//
//	@Override
//	public Iterable<Measure> findAll() {
//		return measureRepository.findAll();
//	}
//
//	@Override
//	public Iterable<Measure> findAllById(Iterable<Integer> ids) {
//		return measureRepository.findAllById(ids);
//	}
//
//	@Override
//	public Optional<Measure> findById(Integer id) {
//		return measureRepository.findById(id);
//	}
//
//	@Override
//	public <S extends Measure> S save(S arg0) {
//		
//	}
//
//	@Override
//	public <S extends Measure> Iterable<S> saveAll(Iterable<S> measures) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Iterable<Measure> findFoodByLabel(String string) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//}
