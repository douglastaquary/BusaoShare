//
//  TripViewCell.swift
//  WhereIsMyBus
//
//  Created by Douglas Taquary on 13/07/20.
//

import SwiftUI

struct TripViewCell: View {
    
    @State var tripNumber: String
    @State var destination: String
    
    var body: some View {
        HStack {
            TripTagView(tripNumber: tripNumber)
            VStack {
                Text(destination)
                    .font(.footnote)
            }
        }
    }
}

struct TripViewCell_Previews: PreviewProvider {
    static var previews: some View {
        TripViewCell(tripNumber: "8000-10", destination: "METRÔ JABAQUARA - CENTRO PARALIMPICO")
    }
}


struct TripTagView: View {
    
    @State var tripNumber: String
    
    var body: some View {
        HStack(spacing: 16) {
            HStack {
                Image(systemName: "bus.fill")
                    .foregroundColor(.secondary)
                Text(tripNumber)
                    .font(.footnote)
                    .fontWeight(.semibold)
            }
            .padding([.leading, .trailing], 6)
            .padding([.top, .bottom], 4)
            .border(Color.gray, width: 0.8)
            .cornerRadius(4)
        }
        .padding(8)

    }
}

struct TripTagView_Previews: PreviewProvider {
    static var previews: some View {
        TripTagView(tripNumber: "8000-10")
    }
}
