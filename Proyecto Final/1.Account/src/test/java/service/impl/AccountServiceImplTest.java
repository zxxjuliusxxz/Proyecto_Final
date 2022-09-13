package service.impl;

import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import com.webflux.jfgb.webflux.Application.Services.BankAccount.BankAccountService;
import com.webflux.jfgb.webflux.Domain.BankAccount;
import com.webflux.jfgb.webflux.Infrastructure.Mock.IBankAccountMockRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class AccountServiceImplTest {

    @Mock
    IBankAccountMockRepository bankAccountRepository;

    @InjectMocks
    BankAccountService bankAccountService;

    @Autowired
    private Mono<BankAccount> account;

    @Autowired
    private BankAccount bankAccount;

    @Test
    void listarAccount() {
        Mockito.when(bankAccountService.list());
        account.subscribe(x -> assertEquals(x.getCustomerType(),CustomerTypesEnum.PERSONAL));
        account.subscribe(x -> assertEquals(x.getCustomerType(),CustomerTypesEnum.EMPRESARIAL));
        account.subscribe(x -> assertEquals(x.getCustomerType(),CustomerTypesEnum.PERSONAL_VIP));
        account.subscribe(x -> assertEquals(x.getCustomerType(),CustomerTypesEnum.PERSONAL_PYME));
    }
}