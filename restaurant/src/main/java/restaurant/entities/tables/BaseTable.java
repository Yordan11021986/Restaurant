package restaurant.entities.tables;

import restaurant.entities.drinks.interfaces.Beverages;
import restaurant.entities.healthyFoods.interfaces.HealthyFood;
import restaurant.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

import static restaurant.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static restaurant.common.ExceptionMessages.INVALID_TABLE_SIZE;

public abstract class BaseTable implements Table {

    private Collection<HealthyFood> healthyFood;
    private Collection<Beverages> beverages;
    private int number;
    private int size;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReservedTable;
    private double allPeople;

    public BaseTable(int number, int size, double pricePerPerson) {
        this.number = number;
        this.setSize(size);
        this.pricePerPerson = pricePerPerson;
        this.setHealthyFood(new ArrayList<>());
        this.setBeverages(new ArrayList<>());
        this.setReservedTable(false);
    }

    public void setHealthyFood(Collection<HealthyFood> healthyFood) {
        this.healthyFood = healthyFood;
    }

    public void setBeverages(Collection<Beverages> beverages) {
        this.beverages = beverages;
    }

    @Override
    public int getTableNumber() {
        return this.number;
    }

    @Override
    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        if (size < 0) {
            throw new IllegalArgumentException(INVALID_TABLE_SIZE);
        }
        this.size = size;
    }

    @Override
    public int numberOfPeople() {
        return this.numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        if (numberOfPeople <= 0) {
            throw new IllegalArgumentException(INVALID_NUMBER_OF_PEOPLE);
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public double pricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReservedTable() {
        return this.isReservedTable;
    }

    public void setReservedTable(boolean reservedTable) {
        isReservedTable = reservedTable;
    }

    @Override
    public double allPeople() {
        return this.allPeople * this.numberOfPeople;
    }

    public void setAllPeople(double allPeople) {
        this.allPeople = allPeople;
    }

    @Override
    public void reserve(int numberOfPeople) {
        setNumberOfPeople(numberOfPeople);
        setAllPeople(this.pricePerPerson * this.numberOfPeople);
        this.isReservedTable = true;
    }

    @Override
    public void orderHealthy(HealthyFood food) {
        this.healthyFood.add(food);
    }

    @Override
    public void orderBeverages(Beverages beverage) {
        this.beverages.add(beverage);
    }

    @Override
    public double bill() {
        double currentBill = 0;

        for (HealthyFood food : healthyFood) {
            currentBill += food.getPrice();
        }

        for (Beverages currentBeverage : beverages) {
            currentBill += currentBeverage.getPrice();
        }

        return currentBill + this.allPeople;
    }

    @Override
    public void clear() {
        healthyFood.clear();
        beverages.clear();
        this.numberOfPeople = 0;
        this.allPeople = 0;
        this.isReservedTable = false;
    }

    @Override
    public String tableInformation() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("Table - %d", getTableNumber())).append(System.lineSeparator());
        stringBuilder.append(String.format("Size - %d", getSize())).append(System.lineSeparator());
        stringBuilder.append(String.format("Type - %s", getClass().getSimpleName())).append(System.lineSeparator());
        stringBuilder.append(String.format("All price - %.2f", pricePerPerson())).append(System.lineSeparator());

        return stringBuilder.toString();
    }
}
