import { PieChart, Pie, Cell, ResponsiveContainer, Legend, Tooltip } from 'recharts'
import { PortfolioDistribution as Distribution } from '../types'

interface PortfolioDistributionProps {
  distribution: Distribution[]
}

function PortfolioDistribution({ distribution }: PortfolioDistributionProps) {
  const formatCurrency = (value: number) => {
    return new Intl.NumberFormat('es-AR', {
      style: 'currency',
      currency: 'USD',
      minimumFractionDigits: 2,
    }).format(value)
  }

  const colors = ['#14b8a6', '#fbbf24', '#3b82f6', '#a855f7']

  const data = distribution.map((item, index) => ({
    ...item,
    color: colors[index % colors.length],
  }))

  return (
    <div className="bg-white dark:bg-gray-800 rounded-lg p-6 shadow-sm">
      <h2 className="text-xl font-bold text-gray-900 dark:text-white mb-6">
        Distribución del Portafolio
      </h2>

      <div className="flex flex-col items-center">
        <ResponsiveContainer width="100%" height={300}>
          <PieChart>
            <Pie
              data={data}
              cx="50%"
              cy="50%"
              innerRadius={60}
              outerRadius={100}
              paddingAngle={2}
              dataKey="value"
            >
              {data.map((entry, index) => (
                <Cell key={`cell-${index}`} fill={entry.color} />
              ))}
            </Pie>
            <Tooltip
              formatter={(value: number) => formatCurrency(value)}
              contentStyle={{
                backgroundColor: 'rgba(0, 0, 0, 0.8)',
                border: 'none',
                borderRadius: '8px',
                color: '#fff',
              }}
            />
          </PieChart>
        </ResponsiveContainer>

        <div className="mt-6 w-full space-y-2">
          {data.map((item, index) => (
            <div key={item.symbol} className="flex items-center justify-between">
              <div className="flex items-center gap-2">
                <div
                  className="w-3 h-3 rounded-full"
                  style={{ backgroundColor: item.color }}
                />
                <span className="text-sm font-semibold text-gray-900 dark:text-white">
                  {item.symbol}
                </span>
              </div>
              <div className="text-sm text-gray-600 dark:text-gray-400">
                {formatCurrency(item.value)} ({item.percentage.toFixed(1)}%)
              </div>
            </div>
          ))}
        </div>
      </div>
    </div>
  )
}

export default PortfolioDistribution

