/*
swift
// iosMain
import UIKit

class IOSDayOfWeekPicker: NSObject, DayOfWeekPicker, UITableViewDataSource, UITableViewDelegate {
    private var onDaysSelected: ((Set<DayOfWeek>) -> Void)?
    private var selectedDays: Set<DayOfWeek> = []

    func show(initiallySelectedDays: Set<DayOfWeek>, onDaysSelected: @escaping (Set<DayOfWeek>) -> Void) {
        self.onDaysSelected = onDaysSelected
        self.selectedDays = initiallySelectedDays

        let viewController = UIViewController() // You might need to adapt this to your existing view hierarchy
        let tableView = UITableView(frame: viewController.view.bounds, style: .plain)
        tableView.dataSource = self
        tableView.delegate = self
        viewController.view.addSubview(tableView)
        tableView.register(UITableViewCell.self, forCellReuseIdentifier: "Cell")

        // Present the viewController (adapt as needed)
        // ...
    }

    // MARK: - UITableViewDataSource

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return DayOfWeek.allCases.count
    }

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell", for: indexPath)
        let day = DayOfWeek.allCases[indexPath.row]
        cell.textLabel?.text = day.name
        cell.accessoryType = selectedDays.contains(day) ? .checkmark : .none
        return cell
    }

    // MARK: - UITableViewDelegate

    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let day = DayOfWeek.allCases[indexPath.row]
        if selectedDays.contains(day) {
            selectedDays.remove(day)
        } else {
            selectedDays.insert(day)
        }
        tableView.reloadRows(at: [indexPath], with: .automatic)
        onDaysSelected?(selectedDays)
    }
}*/
