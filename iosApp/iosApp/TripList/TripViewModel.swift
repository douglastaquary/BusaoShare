//
//  TripViewModel.swift
//  WhereIsMyBus
//
//  Created by Douglas Taquary on 12/07/20.
//

import Foundation
import Combine
import common

class TripViewModel: ObservableObject {
    
    @Published var searchText = ""
    @Published var trips: [Trip] = []
    @Published var isCheck = false
    @Published var loading = false
    var anyCancellation: AnyCancellable?
    
    private let repository: SPTransAPIRepository
    init(repository: SPTransAPIRepository) {
        self.repository = repository
        //checkAuth()
    }
    
}

extension TripViewModel {
    
    func checkAuth() {
        loading = true
        repository.authenticationRequest() { data, error in
            if let result = data {
                print(result)
            }else {
                print("Error when authenticate")
            }
        }

//        anyCancellation = repository.authenticationRequest()
////            .mapError({ (error) -> Error in
////                print(error)
////                self.loading = false
////                return error
////            })
//            .sink(receiveCompletion: { _ in },
//                  receiveValue: { _ in
//                    print("\n✅ App autenticado na API da SPTrans!\n")
//                    self.loading = false
//                    self.isCheck = true
//                    //self.searchTrips()
//            })
    }
    
    public func searchTrips(text: String)  {
        loading = true
        repository.fetchTrips(searchName: text) { data, error in
            self.loading = false
            if let result = data {
                print("\(result)")
                if (result is ResultSuccess<NSArray>) {
                    guard let successResult = result as? ResultSuccess<NSArray> else {
                        return
                    }
                    guard let trips = successResult.data as? [Trip] else {
                        return
                    }
                    self.trips = trips
                } else {
                    print(result)
                }
            }
            if let errorReal = error {
               print(errorReal)
            }
        }
    }
    
}
