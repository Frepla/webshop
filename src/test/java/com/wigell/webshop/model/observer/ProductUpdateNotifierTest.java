package com.wigell.webshop.model.observer;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ProductUpdateNotifierTest {

    private ProductUpdateNotifier notifier;
    private Observer observer;

    @BeforeEach
    void setUp() {
        notifier = ProductUpdateNotifier.getInstance();
        observer = mock(Observer.class);
    }

    @Test
    void shouldAddObserverToListWhenAddObserverIsCalled() {
        notifier.addObserver(observer);

        notifier.notifyObservers("Test message");
        verify(observer).update("Test message");
    }

    @Test
    void shouldNotAddDuplicateObserver() {
        notifier.addObserver(observer);
        notifier.addObserver(observer);

        notifier.notifyObservers("Test message");
        verify(observer, times(1)).update("Test message");
    }

    @Test
    void shouldNotifyAllObserversWhenNotifyObserversIsCalled() {
        Observer observer2 = mock(Observer.class);
        notifier.addObserver(observer);
        notifier.addObserver(observer2);

        notifier.notifyObservers("Product is being manufactured");

        verify(observer).update("Product is being manufactured");
        verify(observer2).update("Product is being manufactured");
    }

    @Test
    void shouldNotNotifyObserversWhenNoObserversArePresent() {
        notifier.notifyObservers("Product is being manufactured");

        verify(observer, times(0)).update(anyString());
    }

    @Test
    void shouldNotifyObserversWhenProductIsManufactured() {
        notifier.addObserver(observer);

        notifier.productManufactured("Pants");

        verify(observer).update("Product is being manufactured: Pants");
    }

    @Test
    void shouldNotifyObserversWhenProductIsReadyForDelivery() {
        notifier.addObserver(observer);

        notifier.productReady("TShirt");

        verify(observer).update("Product is completed and ready for delivery: TShirt");
    }
}