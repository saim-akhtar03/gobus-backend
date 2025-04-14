package com.tickets.GoBus.service.impl;

import com.tickets.GoBus.config.JwtProvider;
import com.tickets.GoBus.exceptions.BusesException;
import com.tickets.GoBus.model.BusOperators;
import com.tickets.GoBus.model.Buses;
import com.tickets.GoBus.model.Routes;
import com.tickets.GoBus.model.Seats;
import com.tickets.GoBus.repository.BusOperatorsRepository;
import com.tickets.GoBus.repository.BusesRepository;
import com.tickets.GoBus.repository.RoutesRepository;
import com.tickets.GoBus.repository.SeatsRepository;
import com.tickets.GoBus.request.CreateBusRequsest;
import com.tickets.GoBus.service.BusOperatorsService;
import com.tickets.GoBus.service.BusesService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@AllArgsConstructor
@Service
public class BusesServiceImpl implements BusesService {
    private final BusesRepository busesRepository;
    private final BusOperatorsService busOperatorsService;
    private final SeatsRepository seatsRepository;
    private final JwtProvider jwtProvider;
    private final BusOperatorsRepository busOperatorsRepository;
    private final RoutesRepository routesRepository;
    @Override
    public Buses createBus(CreateBusRequsest req, String jwt) throws Exception {
        System.out.println(jwt+"jwt");
        String email=jwtProvider.getEmailFromJwtToken(jwt);
        System.out.println(email+"emial");
        BusOperators busOperator=busOperatorsRepository.findByEmail(email);
        System.out.println(busOperator+"bus operator");
        Buses bus=new Buses();
        bus.setBusOperators(busOperator);
        bus.setBusNumber(req.getBusNumber());
        bus.setBusDriverNumber(req.getBusDriverNumber());
        bus.setBusDriverName(req.getBusDriverName());
        bus.setCapacity(req.getCapacity());

        return bus;
    }

    @Override
    public void deleteBus(Long id) throws BusesException {

    Buses bus=this.findByBusId(id);
    busesRepository.delete(bus);

    }

    @Override
    public Buses updateBus(Buses bus,Long busId) throws BusesException {
        Buses existeingBus =this.findByBusId(busId);
        if(bus.getBusNumber()!=null) {
            existeingBus.setBusNumber(bus.getBusNumber());
        }
        if(bus.getBusOperators()!=null) {
            existeingBus.setBusOperators(bus.getBusOperators());
        }
        if(bus.getBusDriverName()!=null) {
            existeingBus.setBusDriverName(bus.getBusDriverName());
        }
        if(bus.getBusDriverNumber()!=null) {
            existeingBus.setBusDriverNumber(bus.getBusDriverNumber());
        }

        return busesRepository.save(existeingBus);
    }

    @Override
    public List<Buses> getAllBuses() {
        return busesRepository.findAll();
    }

    @Override
    public Buses findByBusId(Long id) throws BusesException {
        Buses bus=busesRepository.findByBusId(id);
        if(bus==null){
            throw new BusesException("bus not found with id-"+id);
        }

        return bus;
    }

}
