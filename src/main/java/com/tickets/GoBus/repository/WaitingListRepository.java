package com.tickets.GoBus.repository;

import com.tickets.GoBus.model.WaitingList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WaitingListRepository extends JpaRepository<WaitingList, Long> {}
