package com.example.demo.configuration;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.demo.modules.SheetSource;
import com.example.demo.repository.SheetSourceRepository;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class ConfigurationTest {
  /** Method under test: {@link Configuration#persist(SheetSourceRepository)} */
  @Test
  void testPersist() throws Exception {
    Configuration configuration = new Configuration();
    SheetSourceRepository sheetSourceRepository = mock(SheetSourceRepository.class);
    when(sheetSourceRepository.saveAllAndFlush((Iterable<SheetSource>) any()))
        .thenReturn(new ArrayList<>());
    configuration.persist(sheetSourceRepository).run("foo");
    verify(sheetSourceRepository).saveAllAndFlush((Iterable<SheetSource>) any());
  }
}
