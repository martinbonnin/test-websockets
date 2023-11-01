//
//  main.swift
//  test.ws
//
//  Created by Martin on 30/10/2023.
//

import Foundation


for i in 1...1000 {
    usleep(200_000)
    // Create a websocket with a URL
    let task = URLSession.shared.webSocketTask(with: URL(string: "ws://127.0.0.1:9000")!)
    // Connect, handles handshake
    task.resume()
    
    // Send "Hello!" to the server
    let textMessage = URLSessionWebSocketTask.Message.string("Iteration \(i)")
    task.send(textMessage) { error in /* Handle error */ }
    
    usleep(200_000)
    
    // Close the socket
    task.cancel(with: .goingAway, reason: "ooops".data(using: .utf8))
}
