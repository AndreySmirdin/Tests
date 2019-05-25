import json
import matplotlib.pyplot as plt


def build_time_plot(file):
    with open(file, 'r') as f:
        data = json.load(f)

    tokens_usual, tokens_vol = [], []
    times_usual = []
    times_vol = []

    for item in data:
        if item["benchmark"] == "org.openjdk.jmh.VolatileWrite.incrPlain":
            tokens_usual.append(item["params"]["tokens"])
            times_usual.append(item["primaryMetric"]["score"])
        else:
            tokens_vol.append(item["params"]["tokens"])
            times_vol.append(item["primaryMetric"]["score"])

    plt.plot(tokens_usual, times_usual, label='Not volatile')
    plt.plot(tokens_vol, times_vol, label='Volatile')
    plt.xlabel("tokens")
    plt.ylabel("Time, ns")
    plt.savefig("times.png")


if __name__ == "__main__":
    build_time_plot('build/reports/jmh/results.json')
