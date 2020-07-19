package ditest.beantest;

import ditest.annotation.Component;
import ditest.annotation.Scope;
import ditest.types.ScopeType;

@Scope(ScopeType.PROTOTYPE)
@Component
public class PrototypeTestBean {
}
