package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
  //@Autowired private MemberRepository memberRepository; //필드 주입(권장하지 않음)
  private final MemberRepository memberRepository;

//  @Autowired private DiscountPolicy discountPolicy; //Null Pointer Exception 발생
  private final DiscountPolicy discountPolicy; //Null Pointer Exception 발생

//  private final MemberRepository memberRepository = new MemoryMemberRepository();
//  private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//  private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

//  @Autowired //Setter 자동 주입
//  public void setMemberRepository(MemberRepository memberRepository) {
//    this.memberRepository = memberRepository;
//  }
//
//  @Autowired //Setter 자동 주입
//  public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//    this.discountPolicy = discountPolicy;
//  }

//  @Autowired //생성자가 딱 하나 있으면 생략 가능
  //@RequiredArgsConstructor가 자동으로 해당 생성자를 만들어줌.
//  public OrderServiceImpl(MemberRepository memberRepository,
//      DiscountPolicy discountPolicy) {
//    this.memberRepository = memberRepository;
//    this.discountPolicy = discountPolicy;
//  }

  @Override
  public Order createOrder(Long memberId, String itemName, int itemPrice) {
    Member member = memberRepository.findById(memberId);
    int discountPrice = discountPolicy.discount(member, itemPrice);
    return new Order(memberId, itemName, itemPrice, discountPrice);
  }

  //Test 용도
  public MemberRepository getMemberRepository(){
    return memberRepository;
  }
}
