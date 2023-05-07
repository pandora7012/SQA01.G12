package com.bank.bankingweb.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CalculationContollerTest {

    @Mock
    private Model model;

    @InjectMocks
    private CalculationContoller calculationController;

    private HttpSession session;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        session = new MockHttpSession();
    }

    @Test
    void testGetCalculation_WhenAccountNotSetInSession_ShouldRedirectToIndex() {
        String expectedView = "redirect:/index";

        String actualView = calculationController.getcalculation(session);

        assertEquals(expectedView, actualView);
        assertEquals(null, model.getAttribute("error"));
    }

    @Test
    void testGetCalculation_WhenAccountSetInSession_ShouldReturnCalculationView() {
        String expectedView = "calculation";
        session.setAttribute("account", new Object());

        String actualView = calculationController.getcalculation(session);

        assertEquals(expectedView, actualView);
        assertEquals(null, model.getAttribute("error"));
    }

    @Test
    void testPostCalculation_WhenAccountNotSetInSession_ShouldRedirectToIndex() {
        String expectedView = "redirect:/index";

        String actualView = calculationController.postcalculation("1000000", "3", model, session);

        assertEquals(expectedView, actualView);
        assertEquals(null, model.getAttribute("error"));
    }

    @Test
    void testPostCalculation_WhenAmountIsEmpty_ShouldReturnCalculationViewWithError() {
        String expectedView = "calculation";
        String expectedError = "Hãy nhập số tiền mong muốn";
        session.setAttribute("account", new Object());

        String actualView = calculationController.postcalculation("", "3", model, session);

        assertEquals(expectedView, actualView);
        assertEquals(expectedError, model.getAttribute("error"));
    }

    @Test
    void testPostCalculation_WhenAmountIsNotValid_ShouldReturnCalculationViewWithError() {
        String expectedView = "calculation";
        String expectedError = "Số tiền mong muốn không hợp lệ";
        session.setAttribute("account", new Object());

        String actualView = calculationController.postcalculation("abc", "3", model, session);

        assertEquals(expectedView, actualView);
        assertEquals(expectedError, model.getAttribute("error"));
    }

    @Test
    void testPostCalculation_WhenAmountIsBelowMinimumLimit_ShouldReturnCalculationViewWithError() {
        String expectedView = "calculation";
        String expectedError = "Bạn chỉ có thể vay tối thiểu 3000000 VNĐ";
        session.setAttribute("account", new Object());

        String actualView = calculationController.postcalculation("2000000", "3", model, session);

        assertEquals(expectedView, actualView);
        assertEquals(expectedError, model.getAttribute("error"));
    }

    @Test
    void testPostCalculation_WhenAmountIsAboveMaximumLimit_ShouldReturnCalculationViewWithError() {
        String expectedView = "calculation";
        String expectedError = "Bạn chỉ có thể vay tối đa 500000000 VNĐ";
        session.setAttribute("account", new Object());

        String actualView = calculationController.postcalculation("600000000", "3", model, session);

        assertEquals(expectedView, actualView);
        assertEquals(expectedError, model.getAttribute("error"));
    }

    @Test
    void testPostCalculation_WhenTimeIsInvalid_ShouldReturnCalculationViewWithError() {
        String expectedView = "calculation";
        String expectedError = "Lỗi hệ thống";
        session.setAttribute("account", new Object());

        String actualView = calculationController.postcalculation("1000000", "abc", model, session);

        assertEquals(expectedView, actualView);
        assertEquals(expectedError, model.getAttribute("error"));
    }

    @Test
    void testPostCalculation_WhenCalculationIsSuccessful_ShouldReturnCalculationViewWithResult() {
        String expectedView = "calculation";
        session.setAttribute("account", new Object());
        String amount = "1000000";
        String time = "3";

        String actualView = calculationController.postcalculation(amount, time, model, session);

        assertEquals(expectedView, actualView);
        assertEquals(null, model.getAttribute("error"));
        assertEquals(time, model.getAttribute("time"));
        assertEquals(5, model.getAttribute("interest"));
        assertEquals(new BigInteger("333333"), model.getAttribute("root_per_month"));
        assertEquals(new BigInteger("16666"), model.getAttribute("interest_per_month"));
        assertEquals(new BigInteger("350000"), model.getAttribute("total_per_month"));
    }
}

