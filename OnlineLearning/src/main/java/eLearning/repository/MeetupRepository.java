package eLearning.repository;

import org.springframework.data.repository.CrudRepository;
import eLearning.domain.Meetup;

public interface MeetupRepository extends CrudRepository <Meetup, Integer> {
	Meetup findByMeetupId(int id);
}
