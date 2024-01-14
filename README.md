# ReflectionPattern
자바 리플렉션 패턴 실습

Reflection
힙(Heap) 메모리 영역에 로드되어 있는 클래스 타입의 객체를 통해 필드/메서드/생성자를 접근 제어자와 상관없이 사용 할 수 있도록 지원하는 API

컴파일 시점이 아닌 런타임 시점에 동적으로 특정 클래스의 정보를 추출 할 수 있는 기법

주로 프레임워크나 라이브러리 개발시 사용된다.

https://www.baeldung.com/reflections-library

대표적으로 사용하는 라이브러리/프레임워크로 Spring, JUnit, JSON(Jackson) 등이 있다.

chaptor 1. Reflection

힙(Heap) 메모리 영역에 로드되어 있는 클래스 타입의 객체를 통해 필드/메서드/생성자를 접근 제어자와 상관없이 사용 할 수 있도록 지원하는 API

컴파일 시점이 아닌 런타임 시점에 동적으로 특정 클래스의 정보를 추출 할 수 있는 기법

주로 프레임워크나 라이브러리 개발시 사용된다.

https://www.baeldung.com/reflections-library

대표적으로 사용하는 라이브러리/프레임워크로 Spring, JUnit, JSON(Jackson) 등이 있다.


Chaptor 2. Front Contoller Pattern

모든 요청을 단일 Handler(처리기)에서 수행하도록 하는 패턴, 중앙집중식 요청 처리 매커니즘
Spring MVC의 DispatcherServlet이 이 패턴으로 구현되어 있음.
[Client Request] -> [Dispatcher Servlet] -> 요청의 대한 위임 -> Controller1
                                                           -> Controller2		
                                                           -> Controller3

서블릿(Servlet) , javax.servlet - HttpServlet
클라이언트(브라우저)로부터 요청 받은 http 정보의 집합체를 관리하는 클래스(Interface)
HttpServlet를 상속받아 HttpServletRequest, HttpServletResponse를 통해 구현한다.

Forword (server -> internal -> server)
서블릿에서 클라이언트에게 보내지 않고 바로 다른 서블릿으로 요청하는 방식
동일 요청으로 취급하고 처리자를 위임한다는 개념으로 다가가면 이해가 빠르며, 이로 인해 새로운 Request / Response가 생성되지 않고 기존 요청 정보를 공유한다.

Redicrect (server -> client -> server)
서블릿이 클라이언트에게 보낸 뒤 다른 서블릿에게 요청하는 방식
클라이언트를 거치고 서블릿에 도달하면 이는 새로운 요청이다.
그러므로 HttpServletRequest, HttpServletResponse가 새롭게 생성된다.
