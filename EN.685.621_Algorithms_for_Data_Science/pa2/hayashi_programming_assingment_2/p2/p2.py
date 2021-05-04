import pandas as pd
import numpy as np
import tensorflow as tf
from tensorflow.keras import layers, models
import matplotlib.pyplot as plt

if __name__ == '__main__': 

    df_train = pd.read_csv('mnist_train.csv')
    labels_train = df_train['label']
    images_train = df_train.iloc[:,1:]/255.0
    images_train = images_train.to_numpy().reshape(df_train.shape[0], 28, 28, 1)


    model = models.Sequential()
    model.add(layers.Conv2D(32, (3, 3), activation='relu', input_shape=(28, 28, 1)))
    model.add(layers.MaxPooling2D((2, 2)))
    model.add(layers.Conv2D(64, (3, 3), activation='relu'))
    model.add(layers.MaxPooling2D((2, 2)))
    model.add(layers.Conv2D(64, (3, 3), activation='relu'))

    model.add(layers.Flatten())
    model.add(layers.Dense(64, activation='relu'))
    model.add(layers.Dense(10, activation='softmax'))

    print(model.summary())


    model.compile(optimizer='adam',
                loss='sparse_categorical_crossentropy',
                metrics=['accuracy'])

    model.fit(images_train, labels_train, epochs=5)


    df_test = pd.read_csv('mnist_test.csv')
    labels_test = df_test['label']
    images_test = df_test.iloc[:,1:]/255.0
    images_test = images_test.to_numpy().reshape(df_test.shape[0], 28, 28, 1)    

    test_loss, test_acc = model.evaluate(images_test, labels_test, verbose=2)

    print(test_acc)