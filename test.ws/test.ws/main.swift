//
//  main.swift
//  test.ws
//
//  Created by Martin on 30/10/2023.
//

import Foundation

for i in 1...10 {
    print("running \(i)")
    usleep(200_000)
        
    let task = URLSession.shared.webSocketTask(with: URL(string: "ws://127.0.0.1:9000")!)
    task.resume()
    
    let textMessage = URLSessionWebSocketTask.Message.string("Iteration \(i)")

    task.send(textMessage) { error in /* Handle error */ }
    
    usleep(200_000)
    
    task.cancel(with: .goingAway, reason: "ooops".data(using: .utf8))
}

sleep(1)
