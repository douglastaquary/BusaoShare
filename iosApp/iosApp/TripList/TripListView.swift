//
//  ContentView.swift
//  Shared
//
//  Created by Douglas Taquary on 12/07/20.
//

import SwiftUI
import common

struct TripListView: View {
    
    @ObservedObject var viewModel: TripViewModel
    @State private var searchText = ""
    @State private var hideNavBar = true
    @State private var showingCancelButton: Bool = false
    
    var body: some View {
        NavigationView {
            VStack {
                SearchBarView(searchText: $searchText) {
                    self.viewModel.searchTrips(text: searchText)
                }
                if viewModel.loading {
                    ProgressView()
                } else {
                    if viewModel.trips.isEmpty {
                        Spacer()
                        VStack(spacing: 4) {
                            Text("Começe fazendo uma busca")
                                .font(Font.system(size: 18, weight: Font.Weight.bold))
                            Text("Tente buscar por nome, número do ônibus..")
                                .font(Font.footnote)
                                .foregroundColor(.gray)
                        }
                    } else {
                        List {
                            ForEach(viewModel.trips, id: \.tripId) { trip in
                                TripViewCell(
                                    tripNumber: "\(trip.firstPartOfTheSign)-\(trip.secondPartOfTheSign)",
                                    destination: "\(trip.secondaryTerminal) - \(trip.mainTerminal)"
                                )
                                //                                NavigationLink(
                                //                                    destination: TripDatailView(viewModel: TripDetailViewModel(trip: trip)),
                                //                                    label: {
                                //                                        TripViewCell(
                                //                                            tripNumber: "\(trip.firstPartOfTheSign)-\(trip.secondPartOfTheSign)",
                                //                                            destination: "\(trip.secondaryTerminal) - \(trip.mainTerminal)"
                                //                                        )
                                //                                    })
                                
                            }
                        }
                    }
                }
                Spacer()
            }
            .listStyle(GroupedListStyle())
            .navigationBarTitle("Buscar", displayMode: .large)
            .onAppear {
                UITableView.appearance().backgroundColor = .clear
            }
        }
    }
}
