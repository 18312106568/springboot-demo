
package com.mrb.springboot.demo.respository;

import com.mrb.springboot.demo.DemoApplication;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author MRB
 */
public interface UserRespotory extends JpaRepository<DemoApplication.User, Integer>{
}
