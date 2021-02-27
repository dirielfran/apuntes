package com.eareiza.java8.rxJava;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

public class RxJava {

	private List<Integer> lista1;
	private List<Integer> lista2;
	
	
	
	public RxJava() {
		super();
		lista1 = new ArrayList<Integer>();
		lista2 = new ArrayList<Integer>();
		this.llenarListas();
	}
	
	public void llenarListas() {
		for (int i = 0; i < 10; i++) {
			lista1.add(i);
			lista2.add(i);
		}
	}
	
	//notacion lambda
	public void hello() {
	      Observable.just("Hello, World!") // create new observable
	        .subscribe(onNext -> { // subscribe and perform action
	             System.out.println(onNext);   
	        });
	}
	


	//busca los numeros indicados
	public void buscar() {
		//Se crean los observables para ambas listas
		Observable<Integer> obs1= Observable.from(lista1);
		Observable<Integer> obs2= Observable.from(lista2);
		//Utilizamos el metodo merge, permite unir dos observables para un unico resultado
//		Observable.merge(obs1, obs2).subscribe(new Action1<Integer>() {
//			public void call(Integer numero) {
//				if(numero == 3) {
//					System.out.println(numero);
//				}
//			}
//		});
		
		//con notacion lambda		
		Observable.merge(obs1, obs2).filter(x->x==5).subscribe(System.out::println);
	}


	public static void main(String[] args) {
//		List<String> listaCad= new ArrayList<String>();
//		listaCad.add("mito");
//		listaCad.add("code");
//		listaCad.add("Mito Code");
//		
//		//Principio basico de rxJava Observable
//		//Se apoya en el patron observer
//		Observable<String> obs = Observable.from(listaCad); 
//		obs.subscribe(new Action1<String>() {
//			@Override 
//			public void call(String elemento) {
//				System.out.println(elemento);
//			}
//		});
		
		RxJava rx= new RxJava();
		rx.buscar();
		
		rx.hello();
		
		///////////////////////////////////////////////
		
		Observable<Integer> integerObservable = Observable.just(1, 2, 3); // Integer observable
        Observable<String> stringObservable = Observable.just("Hello, ", "World", "!"); // String observable

        
        Subscriber<Integer> mSubscriber = new Subscriber<Integer>() {
            // NOTE THAT ALL THESE ARE CALLED BY THE OBSERVABLE
            @Override
            public void onCompleted() {
                // called when all objects are emitted
                System.out.println("onCompleted called!");
            }

            @Override
            public void onError(Throwable throwable) {
                // called when an error occurs during emitting objects
                System.out.println("onError called!");
            }

            @Override
            public void onNext(Integer integer) {
                // called for each object that is emitted
                System.out.println("onNext called with: " + integer);
            }
        };
        
        integerObservable.subscribe(mSubscriber);

	}

}
